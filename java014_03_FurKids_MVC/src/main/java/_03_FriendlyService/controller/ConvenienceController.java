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

@Controller
@RequestMapping("/_03_FriendlySystem")
@SessionAttributes({ "emptyCb" , "emptyMcb","nowPage"})
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
		String[] cvs = {"餐廳","寵物美容","旅館","寵物寄放"};
		List<String> cvsInsertOption = Arrays.asList(cvs);
		ArrayList<String> cvsAlterOption = new ArrayList<>();
		cvsAlterOption.add("餐廳");
		cvsAlterOption.add("寵物美容");
		cvsAlterOption.add("旅館");
		cvsAlterOption.add("寵物寄放");

		if(emptyCb.getBusChildNo() != null) {
			for(int i = 0; i < 4; i++) {
				if((cvsAlterOption.get(i)).equals(emptyCb.getConItem())) {
					cvsAlterOption.remove(i);
					break;
				}
			}		
		}
		model.addAttribute("cvsAlterOption", cvsAlterOption);	// 修改頁的服務類型
		model.addAttribute("cvsOption", cvsInsertOption);  		// 新增頁的服務類型
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
	
	@PostMapping("/convenience/insert")
	public String addAndUpdateForm(
			@ModelAttribute("emptyMcb") MerchantChildBean mcb
			) {		
		MerchantChildBean newMcb = service.getBusChild(mcb.getBusChildNo());
		newMcb.setBusChildDescription(mcb.getBusChildDescription());
		ConvenienceBean_H cb = mcb.getConvenienceBean_H();
		cb.setBusAccount(newMcb.getBusAccount());
		cb.setBusChildNo(mcb.getBusChildNo());
		service.insertAndUpdate(cb, newMcb);	
		return "redirect:/_03_FriendlySystem/SessionStatus_setComplete";
	}
	
	@PostMapping("/convenience/alter")
	public String alterForm(
			@ModelAttribute("emptyCb") ConvenienceBean_H cb,
			BindingResult result) {			
		service.Update(cb, cb.getMerchantChildBean());	
		return "redirect:/_03_FriendlySystem/SessionStatus_setComplete";
	}
	
	@GetMapping("/deleteConvenience/{no}")
	public String deleteConvenience(Model model,
			@PathVariable Integer no) {
		service.delete(service.getConvenience(no));
		String pageNo = (String) model.getAttribute("nowPage");
		return "redirect:/_03_FriendlySystem/convenience/" + pageNo;
	}
	
	
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
	
	@GetMapping("/insert/{conNo}")
	public String showInsertForm(Model model,
			@PathVariable(value="conNo", required = false) Integer conNo,
			RedirectAttributes redirectAttributes) {	
		MerchantChildBean bean = new MerchantChildBean();
		bean.setBusChildNo(conNo);
		bean.setBusChildName(service.getBusChild(conNo).getBusChildName());
		model.addAttribute("emptyMcb",bean);
		redirectAttributes.addFlashAttribute("iiinsert", "8888");
		String pageNo = (String) model.getAttribute("nowPage");
		return "redirect:/_03_FriendlySystem/convenience/" + pageNo;
	}

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
				is = servletContext.getResourceAsStream("/images/NoImage.png");
				fileName = "/images/NoImage.png";
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
