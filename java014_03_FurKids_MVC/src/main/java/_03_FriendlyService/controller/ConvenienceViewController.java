package _03_FriendlyService.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@SessionAttributes({"AllViewConvenience","item","loginBean","Classify",
	"LoginOK","ResBean","No","Member", "Reservation","showDate"})

public class ConvenienceViewController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ConvenienceService service;
	
	@Autowired
	ReservationService resService;
	
	//直接分類
	@GetMapping("/Reservation/{item}")
	public String list(Model model,
			@PathVariable(value="item" ,required = false) String item) {
		List<ConvenienceBean_H> cb = resService.getViewConvenience(item);
		model.addAttribute("AllViewConvenience",cb);
		model.addAttribute("item",item);
		if(item.equals("寵物旅館")) {
			model.addAttribute("showDate",2);
		}else {
			model.addAttribute("showDate",1);
		}
		return "/_03_FriendlySystem/tourList";
	}
	
	//詳細內容&預約畫面
	@GetMapping("/ViewReservation/{no}")
	public String detailAndReservation(Model model,
			@PathVariable(value="no" ,required = false) Integer no){
		MemberBean mb = null;
		List<PetBean> pet = null;
		ReservationBean resBean = new ReservationBean();
		List<ReservationChildBean> listRcb = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm");
		String nowTime = sdFormat.format(date);
		if(model.getAttribute("Classify") != null ) {
			if(model.getAttribute("Classify").equals(0)) {
				 mb = (MemberBean) model.getAttribute("LoginOK");
				 pet = mb.getPet(); 
				 for(int i = 0; i < pet.size(); i++) {
					 listRcb.add(new ReservationChildBean(pet.get(i).getPetName(), 
							 							  pet.get(i).getPetVariety(),
							 							  pet.get(i).getPetBreed()));
				 }
				 resBean.setCusAccount(mb.getCusAccount());								//會員帳號
				 resBean.setCusName(mb.getCusName());                                   //會員姓名 
				 resBean.setCusTel(mb.getCusTel());										//會員電話
				 resBean.setConAccount(service.getBusChild(no).getBusAccount()); 		//商家帳號
				 resBean.setBusChildEmail(service.getBusChild(no).getBusChildEmail());  //分店Email
				 resBean.setBusChildNo(no); 											//分店編號
				 resBean.setBusChildName(service.getBusChild(no).getBusChildName()); 	//分店名稱
				 resBean.setBusChildAddress(service.getBusChild(no).getBusChildAddress()); //分店地址
				 resBean.setBusChildTel(service.getBusChild(no).getBusChildTel());      //分店電話
				 resBean.setReservationChildBean(listRcb);
				 
			}
		} else {
			 mb = new MemberBean();
		}
		if(service.getConvenience(no).getConItem().equals("餐廳")) {
			model.addAttribute("restaurant", "restaurant");
		}
		resBean.setResTime(nowTime);
		model.addAttribute("No", no);
		model.addAttribute("Member", mb);
		model.addAttribute("ResBean", resBean);
		model.addAttribute("Reservation",service.getBusChild(no));
		
		return "/_03_FriendlySystem/detailAndReservation";
	}
	//確認預約
	@PostMapping("/ViewReservation/{no}")
	public String Reservation(
			@ModelAttribute("ResBean") ReservationBean resBean,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model) {
		int p = (int) model.getAttribute("No");
		if(model.getAttribute("Classify") == null ) {
			redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");		
			return "redirect:/_03_FriendlySystem/ViewReservation/" + p ;
		} else if(!model.getAttribute("Classify").equals(0)) {
			redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");		
			return "redirect:/_03_FriendlySystem/ViewReservation/" + p ;
		}
		
		if(resService.getReservationBeanCusAccount(resBean.getCusAccount(), resBean.getBusChildNo())) {
			redirectAttributes.addFlashAttribute("ResError", "ResError");
			return "redirect:/_03_FriendlySystem/ViewReservation/" + p ;
		}
		
		resBean.setResID(1);
		if(resBean.getResNote().trim() == "") {
			resBean.setResNote("無備註");
		}
		
		if(service.getConvenience(resBean.getBusChildNo()).getConItem().equals("寵物旅館")) {
			resBean.setResTime(null);
		}
		resService.insert(resBean);
		for(int i = 0; i< resBean.getReservationChildBean().size(); i++) {
			if(resBean.getReservationChildBean().get(i).getResName() != null) {
				resBean.getReservationChildBean().get(i).setResID(resService.getReservationBeanPK(resBean));
				resService.insert(resBean.getReservationChildBean().get(i));
			}
		}
		return "/_03_FriendlySystem/tourInfoSuccess";
	}
	
	//預約明細For會員
	@GetMapping("/MemReservationDetail")
	public String memReservationDetail(Model model,RedirectAttributes redirectAttributes) {
		if(model.getAttribute("Classify") == null ) {
			redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		} else if(!model.getAttribute("Classify").equals(0)) {
			redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
		MemberBean mb = (MemberBean) model.getAttribute("LoginOK");
		model.addAttribute("ResInfo", resService.getReservationInfo(mb.getCusAccount()));
		return "/_03_FriendlySystem/tourInfoDetail";
	}
	
	//預約明細For商家
		@GetMapping("/busReservationDetail")
		public String busReservationDetail(Model model,RedirectAttributes redirectAttributes) {
			if(model.getAttribute("Classify") == null ) {
				redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
				return "redirect:/";
			} else if(!model.getAttribute("Classify").equals(1)) {
				redirectAttributes.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
				return "redirect:/";
			}
			MerchantBean mcb = (MerchantBean) model.getAttribute("LoginOK");
			List<ReservationBean> listRb =  resService.getReservationInfoForBus(mcb.getBusAccount());
			model.addAttribute("ResInfo", listRb);
			return "/_03_FriendlySystem/tourInfoDetailForBus";
		}
	
	//刪除預約
	@GetMapping("/reservationDelete/{resID}")
	public String reservationDelete(Model model,@PathVariable Integer resID) {
		for(ReservationChildBean rcb : resService.getReservation(resID).getReservationChildBean()) {
			resService.delete(rcb);
		}
		resService.delete(resService.getReservation(resID));
		if(model.getAttribute("Classify").equals(1)) {
			return "redirect:/_03_FriendlySystem/busReservationDetail";
		} 
			return "redirect:/_03_FriendlySystem/MemReservationDetail";		
		
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
