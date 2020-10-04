package _01_Member.Login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _00_Init.util.GlobalService;
import _01_Member.Login.model.LoginBean;
import _01_Member.Login.validator.LoginBeanValidator;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.MerchantService;

@Controller
@SessionAttributes({"LoginOK", "loginBean", "Classify", "RequestPath"}) 		// 只要有一個屬性物件(識別字串為"LoginOK")有加入model中，類別前標示此註釋@SessionAttributes({"LoginOK"})順便也將此物件抄錄至session中。
public class LoginController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	
	@PostMapping("login")
	public String loginProcessForm(
			@ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAtt
			) {
		
//		String requestPath = (String)model.getAttribute("RequestPath");  // 取的當前頁面的路徑。
		
		LoginBeanValidator validator = new LoginBeanValidator();
		validator.validate(loginBean, result);
		if (result.hasErrors()) {
//			if (requestPath != null) {
//				model.addAttribute("LoginInputError", "欄位未輸入或輸入錯誤，請重新確認");				
//				redirectAtt.addFlashAttribute("LoginError", "欄位未輸入或輸入錯誤，請重新確認");
//				return "redirect:" + requestPath; 	// 導回當前頁面。
////				return requestPath; 	// 導回當前頁面，須將相關表單都加入@SessionAttributes中，否則無法得到空表單。
//			} else {
				model.addAttribute("LoginInputError", "欄位未輸入或輸入錯誤，請重新確認");				
				return "index";
//			}
		}
		String password =loginBean.getPassword();
		MemberBean mb = null;
		MerchantBean mcb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = memberService.checkAccountPassword(loginBean.getUserId(),  
					GlobalService.getMD5Endocing(GlobalService.encryptString(password)));
			mcb = merchantService.checkAccountPassword(loginBean.getUserId(),  
					GlobalService.getMD5Endocing(GlobalService.encryptString(password)));
			
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				model.addAttribute("LoginOK", mb);
				model.addAttribute("Classify", 0);
				redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>登入成功，歡迎蒞臨</Font>");
			} else if (mcb != null) {
				// OK, 登入成功, 將mcb物件放入Session範圍內，識別字串為"LoginOK"
				model.addAttribute("LoginOK", mcb);
				model.addAttribute("Classify", 1);
				redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>登入成功，歡迎蒞臨</Font>");
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				result.rejectValue("invalidCredentials", "", "該帳號不存在或密碼錯誤，請重新確認");
				redirectAtt.addFlashAttribute("LoginError", "該帳號不存在或密碼錯誤，請重新確認");
			}
		} catch (RuntimeException ex) {
			result.rejectValue("invalidCredentials", "", ex.getMessage());
			ex.printStackTrace();
		}
		
		processCookies(loginBean, request, response);
		
		return "redirect:/";
	}

	
	// Cookies處理。
	private void processCookies(LoginBean bean, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		String userId = bean.getUserId();
		String password = bean.getPassword();
		Boolean rm = bean.isRememberMe();
		
		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		if (bean.isRememberMe()) {
			cookieUser = new Cookie("user", userId);
			cookieUser.setMaxAge(7 * 24 * 60 * 60);       // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("user", userId);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
	}
	
}
