package _04_ShoppingCart.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping({"_04_ShoppingCart"})
// 由於要執行status.setComplete();來移除Session範圍的ShoppingCart物件，所以
// @SessionAttributes({ "ShoppingCart" }) 只能單讀編寫該物件的識別字串。
@SessionAttributes({ "ShoppingCart", "loginBean" })
public class RemoveShoppingCartController {

	@Autowired
	ServletContext context;
	@RequestMapping("removeShoppingCart")
	public String removeCart(Model model, WebRequest webRequest, SessionStatus status) {
		status.setComplete();
		webRequest.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		return "redirect:/ThanksForOrdering";
	}
}
