package _01_Member.Registration.controller;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import _01_Member.Registration.validator.MemberBeanValidator_ChangePassword;
import _01_Member.Registration.validator.MemberBeanValidator_Update;
import _01_Member.Registration.validator.MerchantBeanValidator;
import _01_Member.Registration.validator.MerchantBeanValidator_ChangePassword;
import _01_Member.Registration.validator.MerchantBeanValidator_Update;
import _01_Member.Registration.validator.MerchantChildBeanValidator;
import _01_Member.Registration.validator.PetBeanValidator;

@Controller
@SessionAttributes({"loginBean","LoginOK","RequestPath","memberBean","merchantBean","petBean","merchantChildBean"})
public class ManagementController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	
// 日期格式
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true, 10));
	}
	

// 會員管理(會員基本資料)
	@GetMapping("/MemberManagementCenter")
	public String memberManagementSystem(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		
		MemberBean mb = memberService.queryMember(memberBean.getCusAccount());
		model.addAttribute("memberBean", mb);
		
		return "_01_Member/MemberCenter_Member";
	}
	

// 會員基本資料修改
	@GetMapping("/MemberManagementCenter/MemberUpdate")
	public String memberUpdateEmptyForm(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK"); 
		
		MemberBean mb = memberService.queryMember(memberBean.getCusAccount());	
		model.addAttribute("memberBean", mb); 
		
		return "_01_Member/MemberCenter_MemberUpdate"; 
	}
	
	@PostMapping("/MemberManagementCenter/MemberUpdate")
	public String memberUpdateProcessForm(
			@ModelAttribute("memberBean") MemberBean memberBean,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		
		// 修改表單驗證
		MemberBeanValidator_Update validator = new MemberBeanValidator_Update();
		validator.validate(memberBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MemberCenter_MemberUpdate";
		}
		
		MultipartFile memberImage = memberBean.getMemberMultipartFile();
		String originalFilename = memberImage.getOriginalFilename();
		if (memberImage != null && !memberImage.isEmpty()) {
			try {
				byte[] b = memberImage.getBytes();
				Blob blob = new SerialBlob(b);
				memberBean.setCusFileName(originalFilename);
				memberBean.setCusPhoto(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		
		int n = memberService.updateMember(memberBean);
		if (n == 1) {
			model.addAttribute("memberBean", memberBean);
			model.addAttribute("LoginOK", memberBean);
			redirectAtt.addFlashAttribute("UpdateOK", "<Font color='blue'>修改成功</Font>");
		}
		
		return "redirect:/MemberManagementCenter";
	}
	

// 會員密碼修改
	@PostMapping("/MemberManagementCenter/PasswordUpdate")
	public String changeMemberPassword(
			@ModelAttribute("memberBean") MemberBean memberBean,
			HttpServletRequest request,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		
		MemberBean mb = (MemberBean) model.getAttribute("LoginOK");
		
		// 修改表單驗證
		MemberBeanValidator_ChangePassword validator = new MemberBeanValidator_ChangePassword();
		validator.validate(memberBean, result);
		
		// 舊密碼判斷
		String inputOldPassword = request.getParameter("oldPassword");
		String oldPassword = mb.getCusPassword();
				
		if (inputOldPassword != "") {
			inputOldPassword = GlobalService.getMD5Endocing(GlobalService.encryptString(request.getParameter("oldPassword")));
			if (!inputOldPassword.equals(oldPassword)) {
				model.addAttribute("OldPasswordError","舊密碼輸入不一致，請重新確認");
				// 新密碼確認
				if (result.hasErrors()) {
					model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				redirectAtt.addFlashAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				return "redirect:/MemberManagementCenter";
				}
				return "_01_Member/MemberCenter_Member";
			} else if (result.hasErrors()) {
				model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
				return "_01_Member/MemberCenter_Member";
			}
		} else {
			model.addAttribute("OldPasswordError","舊密碼欄不能空白");
			// 新密碼確認
			if (result.hasErrors()) {
				model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				redirectAtt.addFlashAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				return "redirect:/MemberManagementCenter";
			}
			return "_01_Member/MemberCenter_Member";
		}
		
		// 密碼變更成功
		mb.setCusPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(memberBean.getCusPassword())));
		memberService.updateMember(mb);
		
		return "redirect:/PasswordUpdateSuccess_Logout";
	}
	

// -----------------------------------------------------------------------------------------
	
// 商家管理(商家基本資料)
	@GetMapping("/MerchantManagementCenter")
	public String merchantManagementSystem(Model model) {
		MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
		
		MerchantBean mcb = merchantService.queryMerchant(merchantBean.getBusAccount());
		model.addAttribute("merchantBean", mcb);
		
		return "_01_Member/MerchantCenter_Merchant";
	} 
	

// 商家基本資料修改
	@GetMapping("/MerchantManagementCenter/MerchantUpdate")
	public String merchantUpdateEmptyForm(Model model) {
		MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK"); 
		
		MerchantBean mcb = merchantService.queryMerchant(merchantBean.getBusAccount());	
		model.addAttribute("merchantBean", mcb); 
		
		return "_01_Member/MerchantCenter_MerchantUpdate";
	}
	
	@PostMapping("/MerchantManagementCenter/MerchantUpdate")
	public String merchantUpdateProcessForm(
			@ModelAttribute("merchantBean") MerchantBean merchantBean,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {

		// 修改表單驗證
		MerchantBeanValidator_Update validator = new MerchantBeanValidator_Update();
		validator.validate(merchantBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MerchantCenter_MerchantUpdate";
		}
		
		MultipartFile memberImage = merchantBean.getMerchantMultipartFile();
		String originalFilename = memberImage.getOriginalFilename();
		if (memberImage != null && !memberImage.isEmpty()) {
			try {
				byte[] b = memberImage.getBytes();
				Blob blob = new SerialBlob(b);
				merchantBean.setBusFileName(originalFilename);
				merchantBean.setBusPhoto(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		
		int n = merchantService.updateMerchant(merchantBean);
		if (n == 1) {
			model.addAttribute("merchantBean", merchantBean);
			model.addAttribute("LoginOK", merchantBean);
			redirectAtt.addFlashAttribute("UpdateOK", "<Font color='blue'>修改成功</Font>");
		}

		return "redirect:/MerchantManagementCenter";
	}
		

// 商家密碼修改
	@PostMapping("/MerchantManagementCenter/PasswordUpdate")
	public String changeMerchantPassword(
			@ModelAttribute("merchantBean") MerchantBean merchantBean,
			HttpServletRequest request,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		MerchantBean mb = (MerchantBean) model.getAttribute("LoginOK");
		
		// 修改表單驗證
		MerchantBeanValidator_ChangePassword validator = new MerchantBeanValidator_ChangePassword();
		validator.validate(merchantBean, result);
				
		// 舊密碼判斷
		String inputOldPassword = request.getParameter("oldPassword");
		String oldPassword = mb.getBusPassword();
						
		if (inputOldPassword != "") {
			inputOldPassword = GlobalService.getMD5Endocing(GlobalService.encryptString(request.getParameter("oldPassword")));
			if (!inputOldPassword.equals(oldPassword)) {
				model.addAttribute("OldPasswordError","舊密碼輸入不一致，請重新確認");
				// 新密碼確認
				if (result.hasErrors()) {
					model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				redirectAtt.addFlashAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				return "redirect:/MemberManagementCenter";
				}
				return "_01_Member/MerchantCenter_Merchant";
			} else if (result.hasErrors()) {
				model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
				return "_01_Member/MerchantCenter_Merchant";
			}
		} else {
			model.addAttribute("OldPasswordError","舊密碼欄不能空白");
			// 新密碼確認
			if (result.hasErrors()) {
				model.addAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				redirectAtt.addFlashAttribute("PasswordUpdateError", "密碼修改錯誤，請重新確認。");
//				return "redirect:/MemberManagementCenter";
			}
			return "_01_Member/MerchantCenter_Merchant";
		}
				
		// 密碼變更成功
		mb.setBusPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(merchantBean.getBusPassword())));
		merchantService.updateMerchant(mb);
		
		return "redirect:/PasswordUpdateSuccess_Logout";
	}
		
		

// -----------------------------------------------------------------------------------------
	
// 會員寵物管理
	@GetMapping("/PetManagementCenter")
	public String PetManagementSystem(Model model) {
		return "_01_Member/MemberCenter_Pet";
	}
	
	
// 會員寵物資料
	

// 會員寵物資料修改
			

// 會員寵物刪除
			

// -----------------------------------------------------------------------------------------	
	
// 商家分店管理
	@GetMapping("/MerchantChildManagementCenter")
	public String MerchantChildManagementSystem(Model model) {
		return "_01_Member/MerchantCenter_MerchantChild";
	}
	

// 商家分店資料
	

// 商家分店資料修改
				

// 商家分店刪除
				

// -----------------------------------------------------------------------------------------
		
}
