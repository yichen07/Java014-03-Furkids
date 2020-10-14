package _04_ShoppingCart.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_Member.Registration.model.MemberBean;
import _04_ShoppingCart.model.ShoppingCart;

@Controller
@RequestMapping("_04_ShoppingCart")
@SessionAttributes({ "LoginOK", "ShoppingCart", "loginBean"})
public class ShoppingCartController {

	@Autowired
	ServletContext context;
	private final static String SHOW_CART_CONTENT = "_04_ShoppingCart/ShowCartContent"; 
	@GetMapping("ShowCartContent")
	protected String showCartContent(Model model, SessionStatus status, RedirectAttributes redirectAtt) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			status.setComplete();
			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
		return  "_04_ShoppingCart/ShowCartContent";
	}
	
	@GetMapping("checkout")
	protected String checkout(Model model, SessionStatus status, RedirectAttributes redirectAtt) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			status.setComplete();
			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
		return  "_04_ShoppingCart/OrderConfirm";
	}
	@PostMapping("UpdateItem.do")
	protected String UpdateItem(
			@RequestParam("cmd")   String cmd,  
			@RequestParam(value = "p_Id", required = false) Integer  p_Id, 
			@RequestParam(value = "newQty", required = false) Integer  newQty, 
			Model model, 
			RedirectAttributes ra, 
			HttpSession session, SessionStatus status
			) {
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			status.setComplete();
			return "redirect:/";
		}
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		memberBean = null;     // 此敘述測試用
		if (memberBean == null) {
			status.setComplete();
			ra.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
		if (cmd.equalsIgnoreCase("DEL")) {
	        sc.deleteProduct(p_Id); // 刪除購物車內的某項商品
		    return SHOW_CART_CONTENT;
		} else if (cmd.equalsIgnoreCase("MOD")) {
			sc.modifyQty(p_Id, newQty);   // 修改某項商品的數項
		    return SHOW_CART_CONTENT;
		} else {
			return SHOW_CART_CONTENT;
		}

	}
}
