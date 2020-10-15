package _03_FriendlyService.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.validate.ConvenienceAlterValidator;
import _03_FriendlyService.validate.ConvenienceInsertValidator;


@Controller
@RequestMapping("/_03_FriendlySystem")
@SessionAttributes({ "emptyCb" , "emptyMcb","nowPage","AllConvenience","NotConvenience","TotalPages","loginBean" })
public class ConvenienceController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ConvenienceService service;

	@GetMapping("/convenience/{pageNo}")
	public String list(Model model, 
			HttpServletRequest request,
			@PathVariable(value="pageNo" ,required = false) String pageNo) {
		HttpSession session = request.getSession(false);
		MerchantBean mb = null;
		String userId;
		if (session == null || (MerchantBean) session.getAttribute("LoginOK") == null) {
			return "redirect:/";
		} else {
			mb = (MerchantBean) session.getAttribute("LoginOK");
			userId = mb.getBusAccount();
		}

		// 抓傳進來的頁數，如果沒抓到，就從session抓，如果還是沒有，就給預設值第一頁

//		String pageNoStr = request.getParameter("pageNo");
		if (pageNo == null) {
			pageNo =  (String) model.getAttribute("nowPage");
			if (pageNo == null) {
				pageNo = "1";
			}
		}
		// 把上面結果的頁數存進session
		model.addAttribute("nowPage", pageNo);
		Integer intPageNo =  Integer.parseInt(pageNo);
		// 總上架分店
		List<ConvenienceBean_H> allcb = service.getAllConvenience(userId);
		// 限制一次只抓8筆，從頁數去選擇要從哪筆開始抓
		List<ConvenienceBean_H> cb = service.getPageConvenience(userId, intPageNo);
		for(ConvenienceBean_H cbh : cb) {
			if(cbh.getMerchantChildBean().getBusChildAddress().length() >= 7) {
				cbh.setShortAddress(cbh.getMerchantChildBean().getBusChildAddress().substring(0, 6));
			}else {
				cbh.setShortAddress(cbh.getMerchantChildBean().getBusChildAddress());
			}
						
		}
		
		// 尚未上架的商家分店
		List<MerchantChildBean> mcb = service.getNotConvenience(userId);

		// 總頁數
		int n = service.getTotalPages(userId);

		// 如果總上架的分店數是八的倍數且還有未上架的分店
		if (allcb.size() % 8 == 0 && mcb.size() > 0) {
			n = n + 1;
		}
		
		
		//新增用表單
		MerchantChildBean emptyMcb = (MerchantChildBean) model.getAttribute("emptyMcb");
		//修改用表單
		ConvenienceBean_H emptyCb = (ConvenienceBean_H) model.getAttribute("emptyCb");	

		model.addAttribute("emptyMcb", emptyMcb);				// 新增用表單
		model.addAttribute("emptyCb", emptyCb);					// 修改用表單		
		model.addAttribute("AllConvenience", cb);				// 最多一次只抓8筆上架資料
		model.addAttribute("NotConvenience", mcb);				// 尚未上架的商家分店
		model.addAttribute("TotalPages", n);					// 總頁數
		return "/_03_FriendlySystem/convenience";
	}
	
	@GetMapping("/convenience")
	public String getPage(Model model) {
		String page = "1";
		if(model.getAttribute("nowPage") != null) {
			 page = (String) model.getAttribute("nowPage");
		} 
		return "redirect:/_03_FriendlySystem/convenience/" + page;
	}
	//確定新增
	@PostMapping("/convenience/insert") 
	public String addAndUpdateForm(
			@ModelAttribute("emptyMcb") MerchantChildBean mcb,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes
			) {		
		ConvenienceInsertValidator validator = new ConvenienceInsertValidator();
		validator.validate(mcb,result);
		if (result.hasErrors()) {  //如果有錯
			model.addAttribute("inputError","inputError");				
			return "/_03_FriendlySystem/convenience";			
		}
		MerchantChildBean newMcb = service.getBusChild(mcb.getBusChildNo());
		newMcb.setBusChildDescription(mcb.getBusChildDescription());
		ConvenienceBean_H cb = mcb.getConvenienceBean_H();
		cb.setBusAccount(newMcb.getBusAccount());
		cb.setBusChildNo(mcb.getBusChildNo());
		service.insertAndUpdate(cb, newMcb);	
		return "redirect:/_03_FriendlySystem/SessionStatus_setComplete";
	}
	//確定修改
	@PostMapping("/convenience/alter")
	public String alterForm(
			@ModelAttribute("emptyCb") ConvenienceBean_H cb,
			Model model,
			BindingResult result) {	
		ConvenienceAlterValidator validator = new ConvenienceAlterValidator();
		validator.validate(cb, result);
		if (result.hasErrors()) {  //如果有錯
			model.addAttribute("alterError","alterError");				
			return "/_03_FriendlySystem/convenience";			
		}
		service.Update(cb, cb.getMerchantChildBean());	
		return "redirect:/_03_FriendlySystem/SessionStatus_setComplete";
	}
	//刪除
	@GetMapping("/deleteConvenience/{no}")
	public String deleteConvenience(Model model,
			@PathVariable Integer no) {
		service.delete(service.getConvenience(no));
		return "redirect:/_03_FriendlySystem/convenience";
	}
	
	//顯示修改Modal
	@GetMapping("/alter/{conNo}")
	public String showUpdateForm(Model model,
			@PathVariable(value="conNo", required = false) Integer conNo,
			RedirectAttributes redirectAttributes) {	
		ConvenienceBean_H bean = service.getConvenience(conNo);
		model.addAttribute("emptyCb",bean);
		String pageNo = (String) model.getAttribute("nowPage");
		redirectAttributes.addFlashAttribute("aaalert", "8888");
		return "redirect:/_03_FriendlySystem/convenience/" + pageNo;
	}
	//顯示上架Modal
	@GetMapping("/insert/{conNo}")
	public String showInsertForm(Model model,
			@PathVariable(value="conNo", required = false) Integer conNo,
			RedirectAttributes redirectAttributes) {	
		
		redirectAttributes.addFlashAttribute("iiinsert", "8888");
		String pageNo = (String) model.getAttribute("nowPage");
		
		MerchantChildBean bean = new MerchantChildBean();
		bean.setBusChildNo(conNo);
		bean.setBusChildName(service.getBusChild(conNo).getBusChildName());
		model.addAttribute("emptyMcb",bean);
		return "redirect:/_03_FriendlySystem/convenience/" + pageNo;
	}
	
	@ModelAttribute("cvsOption")
	public List<String> cvsOption(){
		String[] cvs = {"餐廳","寵物美容","寵物旅館"};
		List<String> cvsInsertOption = Arrays.asList(cvs);
		return cvsInsertOption;
	}
	
	@ModelAttribute("cvsAlterOption")
	public List<String> cvsAlterOption(Model model){
		ConvenienceBean_H emptyCb = null;
		ArrayList<String> cvsAlterOption = null;
		if(model.getAttribute("emptyCb") != null) {
			 emptyCb = (ConvenienceBean_H) model.getAttribute("emptyCb");			
		
			cvsAlterOption = new ArrayList<>();
			cvsAlterOption.add("餐廳");
			cvsAlterOption.add("寵物美容");
			cvsAlterOption.add("寵物旅館");
			if(emptyCb.getBusChildNo() != null) {
				for(int i = 0; i < 3; i++) {
					if((cvsAlterOption.get(i)).equals(emptyCb.getConItem())) {
						cvsAlterOption.remove(i);
						break;
					}
				}		
			}
		}
		return cvsAlterOption;
	}
	
	//修改空白表單
	@ModelAttribute("emptyCb")
	public ConvenienceBean_H getConvenienceBean_H(
			@PathVariable(value="conNo", required = false) Integer conNo
			) {
		
		ConvenienceBean_H bean = null;
		if (conNo == null) {
			bean = new ConvenienceBean_H();
		} else {
			System.out.println("conNo=" + conNo);
			bean = service.getConvenience(conNo);
		}
		return bean;
	}
	//上架空白表單
	@ModelAttribute("emptyMcb")
	public MerchantChildBean getMerchantChildBean(
			@PathVariable(value="conNo", required = false) Integer conNo
			) {
		
		MerchantChildBean bean = null;
		if (conNo == null) {
			bean = new MerchantChildBean();
		} else {
			System.out.println("conNo=" + conNo);
			bean = service.getBusChild(conNo);
		}
		return bean;
	}
	//撈該分店圖片
	@GetMapping("/getPicture/{id}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, 
			@PathVariable(value="id" ,required = false) Integer id) {
		
		InputStream is = null;
		ResponseEntity<byte[]> re = null;
		HttpHeaders headers = new HttpHeaders();
		String fileName = "";
		MerchantChildBean mcb = service.getBusChild(id);
		try {
			if (mcb != null) {
				Blob busChildPhoto = mcb.getBusChildPhoto();
				if (busChildPhoto != null) {
					is = busChildPhoto.getBinaryStream();
					fileName = mcb.getBusChildFileName();
				}
			}
			if (is == null) {
				is = servletContext.getResourceAsStream("\\resources\\images\\no_image.png");
				fileName = "no_image.png";
			}
			byte[] b = new byte[81920];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			String mimeType = servletContext.getMimeType(fileName);
			MediaType mediaType = MediaType.valueOf(mimeType);
			headers.setContentType(mediaType);
//			re = ResponseEntity.ok(baos.toByteArray());
			re = new ResponseEntity<>(baos.toByteArray(), headers , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return re;
	}
	
}
