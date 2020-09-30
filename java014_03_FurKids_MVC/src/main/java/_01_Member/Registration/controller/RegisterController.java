package _01_Member.Registration.controller;

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _00_Init.util.GlobalService;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.MerchantService;
import _01_Member.Registration.validator.MemberBeanValidator;
import _01_Member.Registration.validator.MerchantBeanValidator;
import _01_Member.Registration.validator.MerchantChildBeanValidator;
import _01_Member.Registration.validator.PetBeanValidator;

@Controller
@SessionAttributes({" "," "," "})
public class RegisterController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;

	
// 會員
	@GetMapping("/_01_Member/MemberRegistration")
	public String memberRegisterEmptyForm (Model model) {
		MemberBean memberBean = new MemberBean();
		model.addAttribute("memberBean", memberBean);
		return "_01_Member/MemberRegistration";
	}
	
	@PostMapping("/_01_Member/MemberRegistration")
	public String memberRegisterProcessForm(
			@ModelAttribute("memberBean") MemberBean memberBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAtt
			) {
		// 註冊表單驗證
		MemberBeanValidator validator = new MemberBeanValidator();
		validator.validate(memberBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MemberRegistration";
		}
		
		// 圖片上傳
		MultipartFile image = memberBean.getMemberMultipartFile();
		String originalFilename = image.getOriginalFilename();
		@SuppressWarnings("unused")
		String fileName = "";
		if (originalFilename.lastIndexOf(".") > -1) {
			fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
		}
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			memberBean.setCusFileName(originalFilename);
		}
		
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (image != null && !image.isEmpty()) {
			try {
				byte[] b = image.getBytes();
				Blob blob = new SerialBlob(b);
				memberBean.setCusPhoto(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
			}
		}
		
		// 密碼加密
		memberBean.setCusPassword(GlobalService.getMD5Endocing(
				GlobalService.encryptString(memberBean.getCusPassword())));
		
		// 帳號重複性判斷
		@SuppressWarnings("unused")
		MerchantBean merchantBean = null;
		String account = memberBean.getCusAccount(); // 先取得使用者輸入的會員帳號(cusAccount)，再作條件判斷。
		if (memberService.accountExists(account) || merchantService.accountExists(account)) {
			result.rejectValue("cusAccount", "", "帳號已存在，請重新輸入");
			return "_01_Member/MemberRegistration";
		} else {
			try {
				int n = memberService.saveMember(memberBean);
				if (n == 1) {
					redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>註冊成功，請開始使用本系統</Font>");					
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ", ex.getMessage()=" + e.getMessage());
				result.rejectValue("cusAccount", "", "發生異常，請通知系統人員..." + e.getMessage());
				return "_01_Member/MemberRegistration";
			}			
		}
		
		return "redirect:/";
	}
	
	
	
// 商家	
	@GetMapping("/_01_Member/MerchantRegistration")
	public String merchantRegisterEmptyForm (Model model) {
		MerchantBean merchantBean = new MerchantBean();
		model.addAttribute("merchantBean", merchantBean);
		return "_01_Member/MerchantRegistration";
	}
	
	@PostMapping("/_01_Member/MerchantRegistration")
	public String mrchantRegisterProcessForm(
			@ModelAttribute("merchantBean") MerchantBean merchantBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAtt
			) {
		// 註冊表單驗證
		MerchantBeanValidator validator = new MerchantBeanValidator();
		validator.validate(merchantBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MerchantRegistration";
		}
		
		// 圖片上傳
				MultipartFile image = merchantBean.getMerchantMultipartFile();
				String originalFilename = image.getOriginalFilename();
				@SuppressWarnings("unused")
				String fileName = "";
				if (originalFilename.lastIndexOf(".") > -1) {
					fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
				}
				if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
					merchantBean.setBusFileName(originalFilename);
				}
				
				// 建立Blob物件，交由 Hibernate 寫入資料庫
				if (image != null && !image.isEmpty()) {
					try {
						byte[] b = image.getBytes();
						Blob blob = new SerialBlob(b);
						merchantBean.setBusPhoto(blob);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
					}
				}
				
				// 密碼加密
				merchantBean.setBusPassword(GlobalService.getMD5Endocing(
						GlobalService.encryptString(merchantBean.getBusPassword())));
				
				// 帳號重複性判斷
				@SuppressWarnings("unused")
				MerchantBean memberBean = null;
				String account = merchantBean.getBusAccount(); // 先取得使用者輸入的商家帳號(busAccount)，再作條件判斷。
				if (memberService.accountExists(account) || merchantService.accountExists(account)) {
					result.rejectValue("busAccount", "", "帳號已存在，請重新輸入");
					return "_01_Member/MerchantRegistration";
				} else {
					try {
						int n = merchantService.saveMerchant(merchantBean);
						if (n == 1) {
							redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>註冊成功，請開始使用本系統</Font>");					
						}
					} catch (Exception e) {
						System.out.println(e.getClass().getName() + ", ex.getMessage()=" + e.getMessage());
						result.rejectValue("busAccount", "", "發生異常，請通知系統人員..." + e.getMessage());
						return "_01_Member/MerchantRegistration";
					}			
				}
		
		return "redirect:/";
	}
	
	
// 會員寵物	
	@GetMapping("/_01_Member/PetRegistration")
	public String petRegisterEmptyForm (Model model) {
		PetBean petBean = new PetBean();
		model.addAttribute("petBean", petBean);
		return "_01_Member/PetRegistration";
	}
	
	@PostMapping("/_01_Member/PetRegistration")
	public String petRegisterProcessForm(
			@ModelAttribute("petBean") PetBean petBean,
			BindingResult result, Model model,
			HttpServletRequest request
			) {
		PetBeanValidator validator = new PetBeanValidator();
		validator.validate(petBean, result);
		if (result.hasErrors()) {
			return "_01_Member/PetRegistration";
		}
		return "_01_Member/PetRegistration";
	}
	
	
// 商家分店	
	@GetMapping("/_01_Member/MerchantChildRegistration")
	public String merchantChildRegisterEmptyForm (Model model) {
		MerchantChildBean merchantChildBean = new MerchantChildBean();
		model.addAttribute("merchantChildBean", merchantChildBean);
		return "_01_Member/MerchantChildRegistration";
	}
	
	@PostMapping("/_01_Member/MerchantChildRegistration")
	public String mrchantChildRegisterProcessForm(
			@ModelAttribute("merchantChildBean") MerchantChildBean merchantChildBean,
			BindingResult result, Model model,
			HttpServletRequest request
			) {
		MerchantChildBeanValidator validator = new MerchantChildBeanValidator();
		validator.validate(merchantChildBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MerchantChildRegistration";
		}
		return "_01_Member/MerchantChildRegistration";
	}


}
