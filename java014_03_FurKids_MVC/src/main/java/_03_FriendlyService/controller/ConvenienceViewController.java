package _03_FriendlyService.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.PetBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.model.ReservationBean;
import _03_FriendlyService.model.ReservationChildBean;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.service.ReservationService;

@Controller
@RequestMapping("/_03_FriendlySystem")

@SessionAttributes({"nowPage","AllViewConvenience","item","TotalPages",
	"loginBean","Classify","LoginOK","Pet123","ResBean"})

public class ConvenienceViewController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ConvenienceService service;
	
	@Autowired
	ReservationService resService;
	
//	@GetMapping("/Reservation/{item}")
//	public String getPage(Model model,
//			@PathVariable(value="item" ,required = false) String item) {
//		String page = "1";
//		if(model.getAttribute("nowPage") != null) {
//			 page = (String) model.getAttribute("nowPage");
//		} 
//		return "redirect:/_03_FriendlySystem/Reservation/" + item + "/" + page;
//	}
	
	//直接分類
	@GetMapping("/Reservation/{item}/{page}")
	public String list(Model model,
			@PathVariable(value="page" ,required = false) String page,
			@PathVariable(value="item" ,required = false) String item) {
		model.addAttribute("nowPage", page);
		Integer intPageNo =  Integer.parseInt(page);
		// 總頁數
		int n = resService.getTotalPages(item);
		model.addAttribute("TotalPages",n);
		List<ConvenienceBean_H> cb = resService.getPageViewConvenience(item, intPageNo);
		model.addAttribute("AllViewConvenience",cb);
		model.addAttribute("item",item);
		return "/_03_FriendlySystem/tourList";
	}
	
	//詳細內容&預約畫面
	@GetMapping("/ViewReservation/{no}")
	public String detailAndReservation(Model model,
			@PathVariable(value="no" ,required = false) Integer no){
//		ReservationBean resBean = new ReservationBean();
//		model.addAttribute("ResBean",resBean);
		MemberBean mb = null;
		List<PetBean> pet = null;
		ReservationBean resBean = new ReservationBean();
		List<ReservationChildBean> listRcb = new ArrayList<>();
		if(model.getAttribute("Classify") != null ) {
			if(model.getAttribute("Classify").equals(0)) {
				 mb = (MemberBean) model.getAttribute("LoginOK");
				 pet = mb.getPet(); 
				 for(int i = 0; i < pet.size(); i++) {
					 listRcb.add(new ReservationChildBean(pet.get(i).getPetName(), 
							 							  pet.get(i).getPetVariety(),
							 							  pet.get(i).getPetBreed()));
				 }
				 resBean.setCusAccount(mb.getCusAccount()); 							//會員帳號
				 resBean.setConAccount(service.getBusChild(no).getBusAccount()); 		//商家帳號
				 resBean.setBusChildNo(no); 											//分店編號
				 resBean.setBusChildName(service.getBusChild(no).getBusChildName()); 	//分店名稱
				 resBean.setBusChildAddress(service.getBusChild(no).getBusChildAddress()); //分店地址
				 resBean.setReservationChildBean(listRcb);
			}
		} else {
			 mb = new MemberBean();
		}
		
		model.addAttribute("Member", mb);
		model.addAttribute("ResBean", resBean);
		model.addAttribute("Reservation",service.getBusChild(no));
		
		return "/_03_FriendlySystem/detailAndReservation";
	}
	
	@PostMapping("/ViewReservation/{no}")
	public String Reservation(
			@ModelAttribute("ResBean") ReservationBean resBean,
			BindingResult result,
			Model model) {
		if(model.getAttribute("Classify") == null ) {
			return "index";
		} else if(!model.getAttribute("Classify").equals(0)) {
			return "index";
		}
		
		System.out.println(resBean.getResDate());
		return "/_03_FriendlySystem/detailAndReservation";
	}
	
	
	//撈該分店圖片
		@GetMapping("/getSmallPicture/{id}")
		public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, 
				@PathVariable(value="id" ,required = false) String id) {
			
			InputStream is = null;
			ResponseEntity<byte[]> re = null;
			HttpHeaders headers = new HttpHeaders();
			String fileName = "";
			MerchantBean mb = service.getBus(id);
			try {
				if (mb != null) {
					Blob busPhoto = mb.getBusPhoto();
					if (busPhoto != null) {
						is = busPhoto.getBinaryStream();
						fileName = mb.getBusFileName();
					}
				}
				if (is == null) {
					is = servletContext.getResourceAsStream("/resources/images/NoImage.jpg");
					fileName = "/resources/images/NoImage.jpg";
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
//				re = ResponseEntity.ok(baos.toByteArray());
				re = new ResponseEntity<>(baos.toByteArray(), headers , HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return re;
		}
	
}
