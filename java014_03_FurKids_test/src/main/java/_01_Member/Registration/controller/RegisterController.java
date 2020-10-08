package _01_Member.Registration.controller;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
public class RegisterController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	
// 日期格式
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true, 10));
	}
	
	
// 會員註冊
	@GetMapping("/MemberRegistration")
	public String memberRegisterEmptyForm (Model model) {
//		model.addAttribute("RequestPath","/_01_Member/MemberRegistration");
		MemberBean memberBean = new MemberBean();
		model.addAttribute("memberBean", memberBean);
		return "_01_Member/MemberRegistration";
	}
	
	@PostMapping("/MemberRegistration")
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
				System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
				result.rejectValue("cusAccount", "", "發生異常，請通知系統人員...");
				return "_01_Member/MemberRegistration";
			}			
		}
		
		return "redirect:/";
	}

// 會員修改
	@GetMapping("/MemberModify")
	public String memberModifyEmptyForm (Model model) {
		MemberBean memberBean = new MemberBean();
		model.addAttribute("memberBean", memberBean);
		return "_01_Member/MemberModify";
	}
		
	@PostMapping("/MemberModify")
	public String memberModifyProcessForm(
			@ModelAttribute("memberBean") MemberBean memberBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAtt
			) {
		// 修改資料驗證
		MemberBeanValidator validator = new MemberBeanValidator();
		validator.validate(memberBean, result);
		if (result.hasErrors()) {
			return "_01_Member/MemberModify";
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
		String account = memberBean.getCusAccount(); // 先取得使用者輸入的會員帳號(cusAccount)，再作條件判斷。
		if (memberService.accountExists(account) || merchantService.accountExists(account)) {
			result.rejectValue("cusAccount", "", "此名稱已存在，請重新輸入");
			return "_01_Member/MemberModify";
		} else {
			try {
				int n = memberService.saveMember(memberBean);
				if (n == 1) {
					redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>修改成功</Font>");					
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
				result.rejectValue("cusAccount", "", "發生異常，請通知系統人員...");
				return "_01_Member/MemberModify";
			}			
		}
			
		return "redirect:/";
	}	
	
// 會員管理
	@GetMapping("/MemberManagementCenter")
	public String memberManagementSystem(Model model) {
		return "_01_Member/MemberCenter_Member";
	}
	
	
// 商家註冊	
	@GetMapping("/MerchantRegistration")
	public String merchantRegisterEmptyForm (Model model) {
//		model.addAttribute("RequestPath","/_01_Member/MerchantRegistration");
		MerchantBean merchantBean = new MerchantBean();
		model.addAttribute("merchantBean", merchantBean);
		return "_01_Member/MerchantRegistration";
	}
	
	@PostMapping("/MerchantRegistration")
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
						System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
						result.rejectValue("busAccount", "", "發生異常，請通知系統人員...");
						return "_01_Member/MerchantRegistration";
					}			
				}
		
		return "redirect:/";
	}

	// 商家修改
		@GetMapping("/MerchantModify")
		public String merchantModifyEmptyForm (Model model) {
			MerchantBean merchantBean = new MerchantBean();
			model.addAttribute("merchantBean", merchantBean);
			return "_01_Member/MerchantModify";
		}
			
		@PostMapping("/MerchantModify")
		public String merchantModifyProcessForm(
				@ModelAttribute("merchantBean") MerchantBean merchantBean,
				BindingResult result, 
				Model model,
				HttpServletRequest request,
				RedirectAttributes redirectAtt
				) {
			// 修改資料驗證
			MemberBeanValidator validator = new MemberBeanValidator();
			validator.validate(merchantBean, result);
			if (result.hasErrors()) {
				return "_01_Member/MerchantModify";
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
			String account = merchantBean.getBusAccount(); // 先取得使用者輸入的會員帳號(busAccount)，再作條件判斷。
			if (memberService.accountExists(account) || merchantService.accountExists(account)) {
				result.rejectValue("busAccount", "", "此名稱已存在，請重新輸入");
				return "_01_Member/MerchantModify";
			} else {
				try {
					int n = merchantService.saveMerchant(merchantBean);
					if (n == 1) {
						redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>修改成功</Font>");					
					}
				} catch (Exception e) {
					System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
					result.rejectValue("busAccount", "", "發生異常，請通知系統人員...");
					return "_01_Member/MerchantModify";
				}			
			}
				
			return "redirect:/";
		}		
	
// 商家管理
	@GetMapping("/MerchantManagementCenter")
	public String merchantManagementSystem(Model model) {
		return "_01_Member/MerchantCenter_Merchant";
	}
	
// 會員寵物新增	
	@GetMapping("/PetRegistration")
	public String petRegisterEmptyForm (Model model) {
//		model.addAttribute("RequestPath","/_01_Member/PetRegistration");
		PetBean petBean = new PetBean();
		model.addAttribute("petBean", petBean);
		return "_01_Member/PetRegistration";
	}
	
	@PostMapping("/PetRegistration")
	public String petRegisterProcessForm(
			@ModelAttribute("petBean") PetBean petBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAtt
			) {
		
		// 先取得當前會員登入的帳號加入petBean中
		String cusAccount = "";
		MemberBean memberBean = (MemberBean)model.getAttribute("LoginOK");
		if (memberBean == null) {
			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/PetRegistration";
		} else {
			cusAccount = memberBean.getCusAccount();
//			petBean.setMemberBean(memberBean);
			petBean.setCusAccount(cusAccount);
		}
		
		// 註冊表單驗證
		PetBeanValidator validator = new PetBeanValidator();
		validator.validate(petBean, result);
		if (result.hasErrors()) {
			return "_01_Member/PetRegistration";
		}
		
		// 圖片上傳
		MultipartFile image = petBean.getPetMultipartFile();
		String originalFilename = image.getOriginalFilename();
		@SuppressWarnings("unused")
		String fileName = "";
		if (originalFilename.lastIndexOf(".") > -1) {
			fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
		}
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			petBean.setPetFileName(originalFilename);
		}
		
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (image != null && !image.isEmpty()) {
			try {
				byte[] b = image.getBytes();
				Blob blob = new SerialBlob(b);
				petBean.setPetPhoto(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
			}
		}
		
		try {
			int n = memberService.savePet(petBean);
			if (n == 1) {
				redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>新增成功，請開始使用本系統</Font>");					
			}
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
			result.rejectValue("petName", "", "發生異常，請通知系統人員..." + e.getMessage());
		}			
		return "redirect:/PetRegistration";
	}

	// 會員寵物修改	
		@GetMapping("/PetModify")
		public String petModifyEmptyForm (Model model) {
//			model.addAttribute("RequestPath","/_01_Member/PetModify");
			PetBean petBean = new PetBean();
			model.addAttribute("petBean", petBean);
			return "_01_Member/PetModify";
		}
		
		@PostMapping("/PetModify")
		public String petModifyProcessForm(
				@ModelAttribute("petBean") PetBean petBean,
				BindingResult result, 
				Model model,
				HttpServletRequest request,
				RedirectAttributes redirectAtt
				) {
			
			// 先取得當前會員登入的帳號加入petBean中
			String cusAccount = "";
			MemberBean memberBean = (MemberBean)model.getAttribute("LoginOK");
			if (memberBean == null) {
				redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
				return "redirect:/PetModify";
			} else {
				cusAccount = memberBean.getCusAccount();
//				petBean.setMemberBean(memberBean);
				petBean.setCusAccount(cusAccount);
			}
			
			// 修改表單驗證
			PetBeanValidator validator = new PetBeanValidator();
			validator.validate(petBean, result);
			if (result.hasErrors()) {
				return "_01_Member/PetModify";
			}
			
			// 圖片上傳
			MultipartFile image = petBean.getPetMultipartFile();
			String originalFilename = image.getOriginalFilename();
			@SuppressWarnings("unused")
			String fileName = "";
			if (originalFilename.lastIndexOf(".") > -1) {
				fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
			}
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				petBean.setPetFileName(originalFilename);
			}
			
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (image != null && !image.isEmpty()) {
				try {
					byte[] b = image.getBytes();
					Blob blob = new SerialBlob(b);
					petBean.setPetPhoto(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
				}
			}
			
			try {
				int n = memberService.savePet(petBean);
				if (n == 1) {
					redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>新增成功，請開始使用本系統</Font>");					
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
				result.rejectValue("petName", "", "發生異常，請通知系統人員..." + e.getMessage());
			}			
			return "redirect:/PetModify";
		}	
	
// 會員寵物管理
	@GetMapping("/PetManagementCenter")
	public String PetManagementSystem(Model model) {
		return "_01_Member/MemberCenter_Pet";
	}
	
	// 商家分店新增	
		@GetMapping("/MerchantChildRegistration")
		public String merchantChildRegisterEmptyForm (Model model) {
//			model.addAttribute("RequestPath","/_01_Member/MerchantChildRegistration");
			MerchantChildBean merchantChildBean = new MerchantChildBean();
			model.addAttribute("merchantChildBean", merchantChildBean);
			return "_01_Member/MerchantChildRegistration";
		}
		
		@PostMapping("/MerchantChildRegistration")
		public String mrchantChildRegisterProcessForm(
				@ModelAttribute("merchantChildBean") MerchantChildBean merchantChildBean,
				BindingResult result, 
				Model model,
				HttpServletRequest request,
				RedirectAttributes redirectAtt
				) {
			// 先取得當前商家登入的帳號加入merchantChildBean中
			String busAccount = "";
			MerchantBean merchantBean = (MerchantBean)model.getAttribute("LoginOK");
			if (merchantBean == null) {
				redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入商家帳號");
				return "redirect:/MerchantChildRegistration";
			} else {
				busAccount = merchantBean.getBusAccount();
//				merchantChildBean.setMerchantBean(merchantBean);
				merchantChildBean.setBusAccount(busAccount);
			}
			
			// 註冊表單驗證
			MerchantChildBeanValidator validator = new MerchantChildBeanValidator();
			validator.validate(merchantChildBean, result);
			if (result.hasErrors()) {
				return "_01_Member/MerchantChildRegistration";
			}
			
			// 圖片上傳
			MultipartFile image = merchantChildBean.getMerchantChildMultipartFile();
			String originalFilename = image.getOriginalFilename();
			@SuppressWarnings("unused")
			String fileName = "";
			if (originalFilename.lastIndexOf(".") > -1) {
				fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
			}
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				merchantChildBean.setBusChildFileName(originalFilename);
			}
					
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (image != null && !image.isEmpty()) {
				try {
					byte[] b = image.getBytes();
					Blob blob = new SerialBlob(b);
					merchantChildBean.setBusChildPhoto(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
				}
			}
			
			// 商家分店重複性判斷
			String address = merchantChildBean.getBusChildAddress(); // 先取得使用者輸入的商家分店地址(busChildAddress)，再作條件判斷。
			if (merchantService.merchantChildExists(busAccount, address)) {
				result.rejectValue("busChildAddress", "", "商家分店已存在，請重新輸入");
				return "_01_Member/MerchantChildRegistration";
			} else {
				try {
					int n = merchantService.saveMerchantChild(merchantChildBean);
					if (n == 1) {
						redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>新增成功，請開始使用本系統</Font>");					
					}
				} catch (Exception e) {
					System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
					result.rejectValue("busChildName", "", "發生異常，請通知系統人員...");
				}			
			}
			
			return "redirect:/MerchantChildRegistration";
		}

		// 商家分店修改	
		@GetMapping("/MerchantChildModify")
		public String merchantChildModifyEmptyForm (Model model) {
//			model.addAttribute("RequestPath","/_01_Member/MerchantChildModify");
			MerchantChildBean merchantChildBean = new MerchantChildBean();
			model.addAttribute("merchantChildBean", merchantChildBean);
			return "_01_Member/MerchantChildModify";
		}
		
		@PostMapping("/MerchantChildModify")
		public String merchantChildModifyProcessForm(
				@ModelAttribute("merchantChildBean") MerchantChildBean merchantChildBean,
				BindingResult result, 
				Model model,
				HttpServletRequest request,
				RedirectAttributes redirectAtt
				) {
			// 先取得當前商家登入的帳號加入merchantChildBean中
			String busAccount = "";
			MerchantBean merchantBean = (MerchantBean)model.getAttribute("LoginOK");
			if (merchantBean == null) {
				redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入商家帳號");
				return "redirect:/MerchantChildModify";
			} else {
				busAccount = merchantBean.getBusAccount();
//				merchantChildBean.setMerchantBean(merchantBean);
				merchantChildBean.setBusAccount(busAccount);
			}
			
			// 註冊表單驗證
			MerchantChildBeanValidator validator = new MerchantChildBeanValidator();
			validator.validate(merchantChildBean, result);
			if (result.hasErrors()) {
				return "_01_Member/MerchantChildModify";
			}
			
			// 圖片上傳
			MultipartFile image = merchantChildBean.getMerchantChildMultipartFile();
			String originalFilename = image.getOriginalFilename();
			@SuppressWarnings("unused")
			String fileName = "";
			if (originalFilename.lastIndexOf(".") > -1) {
				fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
			}
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				merchantChildBean.setBusChildFileName(originalFilename);
			}
					
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (image != null && !image.isEmpty()) {
				try {
					byte[] b = image.getBytes();
					Blob blob = new SerialBlob(b);
					merchantChildBean.setBusChildPhoto(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常：" + e.getMessage());
				}
			}		
		
		// 商家分店重複性判斷
		String address = merchantChildBean.getBusChildAddress(); // 先取得使用者輸入的商家分店地址(busChildAddress)，再作條件判斷。
		if (merchantService.merchantChildExists(busAccount, address)) {
			result.rejectValue("busChildAddress", "", "商家分店已存在，請重新輸入");
			return "_01_Member/MerchantChildModify";
		} else {
			try {
				int n = merchantService.saveMerchantChild(merchantChildBean);
				if (n == 1) {
					redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>新增成功，請開始使用本系統</Font>");					
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ", e.getMessage()=" + e.getMessage());
				result.rejectValue("busChildName", "", "發生異常，請通知系統人員...");
			}			
		}
		
		return "redirect:/MerchantChildModify";
	}

// 商家分店管理
	@GetMapping("/MerchantChildManagementCenter")
	public String MerchantChildManagementSystem(Model model) {
		return "_01_Member/MerchantCenter_MerchantChild";
	}
	
}
