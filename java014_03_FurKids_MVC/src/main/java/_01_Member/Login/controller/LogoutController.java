package _01_Member.Login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;

@Controller
@SessionAttributes({"LoginOK", "Classify"}) 
public class LogoutController {
	
	@GetMapping("/logout")
	public String logout(
			HttpSession session,  
			Model model, 
			SessionStatus status,
			RedirectAttributes redirectAtt
			) {
		String name = "";
		
		if ((Integer)model.getAttribute("Classify") == 0) {
			MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
			name = memberBean.getCusName();
		} else if ((Integer)model.getAttribute("Classify") == 1) {
			MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
			name = merchantBean.getBusName();
		} else {
			name = "訪客";
		}
		String farewellMessage = "<Font color='blue'>" + name + "您已登出，期待您再次蒞臨本網站</Font>";
		redirectAtt.addFlashAttribute("FlashMSG_farewell", farewellMessage);
		
		// 登出時執行下列兩敘述
		status.setComplete();		// 移除@SessionAttributes({"LoginOK"}) 標示的屬性物件
		session.invalidate();		// 此敘述不能省略		
		return "redirect:/";		// 跳轉回http://localhost:8080/Context_Path/
		
//		return "redirect: /";		// 跳轉回http://localhost:8080/
	}
}
