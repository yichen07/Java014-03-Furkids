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
@SessionAttributes({"LoginOK", "loginBean", "Classify"}) 		// 只要有一個屬性物件(識別字串為"LoginOK")有加入model中，類別前標示此註釋@SessionAttributes({"LoginOK"})順便也將此物件抄錄至session中。
public class LoginController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	@Autowired
	LoginBean loginBean;
	
	
	
//	// 由Cookie取得使用者登入的資料
////	@GetMapping("login")
//	@ModelAttribute("loginBean")
//	public LoginBean loginEmptyForm(HttpServletRequest request, Model model, 
//		@CookieValue(value="user", required = false) String user, 					// value="user"相當於是Cookie的名稱。
//		@CookieValue(value="password", required = false) String password, 
//		@CookieValue(value="rm", required = false) Boolean rm 
//			) {
//		if (user == null)
//			user = "";
//		if (password == null) {
//			password = "";
//		} else {
//			password = GlobalService.decryptString(GlobalService.KEY, password);
//		}
//		
//		if (rm != null) {
//			rm = Boolean.valueOf(rm);
//		} else {
//			rm = false;
//		}
//			
////		LoginBean loginBean = new LoginBean(user, password, rm);
//		model.addAttribute("loginBean", loginBean);	
//		System.out.println("login");
////		return loginForm;
//		return loginBean;
//	}
	
	@PostMapping("login")
	public String loginProcessForm(
			@ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult result, 
			Model model,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAtt
			) {
		
		HttpSession session = request.getSession();
		String nextPath = (String)session.getAttribute("requestURI");
		
		if (nextPath == null) {
			nextPath = request.getContextPath();
		}
		
		LoginBeanValidator validator = new LoginBeanValidator();
		validator.validate(loginBean, result);
		if (result.hasErrors()) {
//			return "/index";
			return "redirect: " + nextPath;
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
				redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>登入成功，請開始使用本系統</Font>");
			} else if (mcb != null) {
				// OK, 登入成功, 將mcb物件放入Session範圍內，識別字串為"LoginOK"
				model.addAttribute("LoginOK", mcb);
				model.addAttribute("Classify", 1);
				redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>登入成功，請開始使用本系統</Font>");
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				result.rejectValue("invalidCredentials", "", "該帳號不存在或密碼錯誤");
//				return "redirect: " + nextPath;
			}
		} catch (RuntimeException ex) {
			result.rejectValue("invalidCredentials", "", ex.getMessage());
			ex.printStackTrace();
			return "redirect:" + nextPath;
		}
		
		processCookies(loginBean, request, response);
		
		return "redirect:/";
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
	
//	@PostMapping("login")
//	public String loginProcessForm(
//			Model model,
//			HttpServletRequest request, 
//			HttpServletResponse response,
//			RedirectAttributes redirectAtt
//			) {
//		
//		HttpSession session = request.getSession();
//		
//		// 定義存放錯誤訊息的Map物件
//		Map<String, String> errorMsgMap = new HashMap<String, String>();
//		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
//		model.addAttribute("ErrorMsgKey", errorMsgMap);
//		
//		// 1. 讀取使用者輸入資料
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("pswd");
//		String rm = request.getParameter("rememberMe");
//		String requestURI = (String) session.getAttribute("requestURI");
//		
//		
//		
//		
//		// 2. 進行必要的資料轉換
//		// 無
//		
//		// 3. 檢查使用者輸入資料
//		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
//		if (userId == null || userId.trim().length() == 0) {
//			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
//		}
//		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
//		if (password == null || password.trim().length() == 0) {
//			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
//		}
//
//		// 如果 errorMsgMap 不是空的，表示有錯誤。
//		if (!errorMsgMap.isEmpty()) {
//			
//			if (requestURI != null) {
//				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
//				return requestURI;
//			} else {
//				return "redirect:/";
//			}
////			return "redirect: " + requestURI;
//		}
//		
//		// 4. 進行 Business Logic 運算
//		// 將MemberServiceImpl類別new為物件，存放物件參考的變數為 loginService
//		
//		// 將密碼加密兩次，以便與存放在表格內的密碼比對
//		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
//		
//		
//		MemberBean mb = null;
//		MerchantBean mcb = null;
//		
//		try {
//			// 呼叫 loginService物件的 checkAccountPassword()，傳入userid與password兩個參數
//				mb = memberService.checkAccountPassword(userId, password);				
//				mcb = merchantService.checkAccountPassword(userId, password);
//				
//				if (mb != null) {
//					// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
//					session.setAttribute("LoginOKMsg", "登入成功");
//					session.setAttribute("LoginOK", mb);
//				} else if (mcb != null) {
//					// OK, 登入成功, 將mcb物件放入Session範圍內，識別字串為"LoginOK"
//					session.setAttribute("LoginOKMsg", "登入成功");
//					session.setAttribute("LoginOK", mcb);
//				} else {
//					// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
//					errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
//				}
//		} catch (RuntimeException ex) {
//				errorMsgMap.put("LoginError", ex.getMessage());
//		}
//		
//		processCookies(loginBean, request, response);
//		
//		// 5.依照 Business Logic 運算結果來挑選適當的畫面
//		// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
//		if (errorMsgMap.isEmpty()) {
//			if (requestURI != null) {
//				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
//				return requestURI;
//			} else {
//				return request.getContextPath();
//			}
//		} else {
//			// 如果errorMsgMap不是空的，表示有錯誤，交棒給login.jsp
//			return request.getContextPath();
//		}
//		
//	}
//	
//	private void processCookies(LoginBean bean, HttpServletRequest request, HttpServletResponse response) {
//		Cookie cookieUser = null;
//		Cookie cookiePassword = null;
//		Cookie cookieRememberMe = null;
//		String userId = bean.getUserId();
//		String password = bean.getPassword();
//		Boolean rm = bean.isRememberMe();
//		
//		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
//		if (bean.isRememberMe()) {
//			cookieUser = new Cookie("user", userId);
//			cookieUser.setMaxAge(7 * 24 * 60 * 60);       // Cookie的存活期: 七天
//			cookieUser.setPath(request.getContextPath());
//			
//			String encodePassword = GlobalService.encryptString(password);
//			cookiePassword = new Cookie("password", encodePassword);
//			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
//			cookiePassword.setPath(request.getContextPath());
//			
//			cookieRememberMe = new Cookie("rm", "true");
//			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
//			cookieRememberMe.setPath(request.getContextPath());
//		} else { // 使用者沒有對 RememberMe 打勾
//			cookieUser = new Cookie("user", userId);
//			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
//			cookieUser.setPath(request.getContextPath());
//			
//			String encodePassword = GlobalService.encryptString(password);
//			cookiePassword = new Cookie("password", encodePassword);
//			cookiePassword.setMaxAge(0);
//			cookiePassword.setPath(request.getContextPath());
//			
//			cookieRememberMe = new Cookie("rm", "true");
//			cookieRememberMe.setMaxAge(0);
//			cookieRememberMe.setPath(request.getContextPath());
//		}
//		response.addCookie(cookieUser);
//		response.addCookie(cookiePassword);
//		response.addCookie(cookieRememberMe);
//		
//	}
}
