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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import _00_Init.util.GlobalService;
import _01_Member.Login.model.LoginBean;
import _01_Member.Login.validator.LoginBeanValidator;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.service.MemberService;

@Controller
@RequestMapping("/_02_login")
@SessionAttributes({"LoginOK"}) 		// 只要有一個屬性物件(識別字串為"LoginOK")有加入model中，類別前標示此註釋@SessionAttributes({"LoginOK"})順便也將此物件抄錄至session中。
public class LoginController {
	
	String loginForm = "_02_login\\loginForm";
	String loginOut = "_02_login\\logout";
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login00(HttpServletRequest request, Model model, 
		@CookieValue(value="user", required = false) String user, 					// value="user"相當於是Cookie的名稱。
		@CookieValue(value="password", required = false) String password, 
		@CookieValue(value="rm", required = false) Boolean rm 
			) {
		if (user == null)
			user = "";
		if (password == null) {
			password = "";
		} else {
			password = GlobalService.decryptString(GlobalService.KEY, password);
		}
		
		if (rm != null) {
			rm = Boolean.valueOf(rm);
		} else {
			rm = false;
		}
			
		LoginBean bean = new LoginBean(user, password, rm);
		model.addAttribute(bean);		
		return loginForm;
	}
	
	@PostMapping("/login")
	public String checkAccount(
			@ModelAttribute("loginBean") LoginBean bean,
			BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		LoginBeanValidator validator = new LoginBeanValidator();
		validator.validate(bean, result);
		if (result.hasErrors()) {
			return loginForm;
		}
		String password =bean.getPassword();
		MemberBean mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = memberService.checkIdPassword(bean.getUserId(),  
					GlobalService.getMD5Endocing(GlobalService.encryptString(password)));
			
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				model.addAttribute("LoginOK", mb);
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				result.rejectValue("invalidCredentials", "", "該帳號不存在或密碼錯誤");
				return loginForm;
			}
		} catch (RuntimeException ex) {
			result.rejectValue("invalidCredentials", "", ex.getMessage());
			ex.printStackTrace();
			return loginForm;
		}
		HttpSession session = request.getSession();
		processCookies(bean, request, response);
		String nextPath = (String)session.getAttribute("requestURI");
		if (nextPath == null) {
			nextPath = request.getContextPath();
		}
		return "redirect: " + nextPath;
	}

	
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
