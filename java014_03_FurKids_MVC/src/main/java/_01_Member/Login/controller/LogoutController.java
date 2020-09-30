package _01_Member.Login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_register.model.MemberBean;

@Controller
@RequestMapping("/_02_login")
@SessionAttributes({"LoginOK"}) 
public class LogoutController {
	@GetMapping("/logout")
	public String logout(HttpSession session,  Model model, SessionStatus status,
			RedirectAttributes redirectAtt
			) {
		String name = "";
		System.out.println("Logout");
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean != null) {
			name = memberBean.getName();
		} else {
			name = "訪客";
		}
		String farewellMessage = name + "您已登出，期待您再次蒞臨本網站";
		redirectAtt.addFlashAttribute("FlashMSG_farewell", farewellMessage);
		// 登出時執行下列兩敘述
		status.setComplete();		// 移除@SessionAttributes({"LoginOK"}) 標示的屬性物件
		session.invalidate();		// 此敘述不能省略		
		return "redirect:/";		// 跳轉回http://localhost:8080/Context_Path/
		
//		return "redirect: /";		// 跳轉回http://localhost:8080/
	}
}
