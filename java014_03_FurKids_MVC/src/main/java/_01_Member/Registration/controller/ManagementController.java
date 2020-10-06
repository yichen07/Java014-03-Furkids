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
		model.addAttribute(memberBean);
		return "_01_Member/MemberCenter_Member";
	}
	

// 會員基本資料修改
	@GetMapping("/MemberManagementCenter/MemberUpdate")
	public String memberUpdateEmptyForm(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		model.addAttribute(memberBean);
		return "_01_Member/MemberCenter_MemberUpdate";
	}
	
	@PostMapping("/MemberManagementCenter/MemberUpdate")
	public String memberUpdateProcessForm(
			@ModelAttribute("memberBean") MemberBean memberBean,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		MemberBean mb = (MemberBean) model.getAttribute("LoginOK");
		
		
		
		return "redirect:/MemberManagementCenter";
	}
	

// 會員密碼修改
	@PostMapping("/MemberManagementCenter/PasswordUpdate")
	public String changeMemberPassword(
			@ModelAttribute("memberBean") MemberBean memberBean,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		
		return "redirect:/PasswordUpdateSuccess_Logout";
	}
	

// -----------------------------------------------------------------------------------------
	
// 商家管理(商家基本資料)
	@GetMapping("/MerchantManagementCenter")
	public String merchantManagementSystem(Model model) {
		MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
		model.addAttribute(merchantBean);
		return "_01_Member/MerchantCenter_Merchant";
	} 
	

// 商家基本資料修改
	@GetMapping("/MerchantManagementCenter/MerchantUpdate")
	public String merchantUpdateEmptyForm(Model model) {
		MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
		model.addAttribute(merchantBean);
		return "_01_Member/MerchantCenter_MerchantUpdate";
	}
	
	@PostMapping("/MerchantManagementCenter/MerchantUpdate")
	public String merchantUpdateProcessForm(
			@ModelAttribute("merchantBean") MerchantBean merchantBean,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAtt
			) {
		
		return "redirect:/MerchantManagementCenter";
	}
		

// 商家密碼修改
		
		

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
