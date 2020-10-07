package _02_ShoppingSystem.ShoppingCart.controller;

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
import _01_Member.Registration.model.MerchantBean;
import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;



@Controller
@RequestMapping("_02_ShoppingSystem")
@SessionAttributes({ "LoginOK", "ShoppingCart" , "loginBean"})
public class ShoppingCartController {

	@Autowired
	ServletContext context;
	private final static String SHOW_CART_CONTENT = "_02_ShoppingSystem/ShowCartContent"; 
	@GetMapping("ShowCartContent")
	protected String showCartContent(Model model, SessionStatus status,
			      RedirectAttributes redirectAtt
       ) {
//		Object obj = model.getAttribute("LoginOK");
//        
//		if(obj == null) {
//			status.setComplete();
// 			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
//			return "redirect:/_01_Member/PetRegistration";		}
        
//		if (memberBean == null && merchantBean == null){
//			status.setComplete();
// 			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
//			return "redirect:/_01_Member/PetRegistration";
//       } 
				
		return  "_02_ShoppingSystem/ShowCartContent";
	}
	
	@GetMapping("checkout")
	protected String checkout(Model model, SessionStatus status,
            RedirectAttributes redirectAtt
    ) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//        MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
		Object obj = model.getAttribute("LoginOK");
		//MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		//MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
        
		
        if (obj == null){
			status.setComplete();
 			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/_02_ShoppingSystem/ShowCartContent";
       } 
		return  "_02_ShoppingSystem/OrderConfirm";
	}
	@PostMapping("UpdateItem.do")
	protected String UpdateItem(
			@RequestParam("cmd")   String cmd,  
			@RequestParam(value = "ComId", required = false) Integer  ComId, 
			@RequestParam(value = "newQty", required = false) Integer  newQty, 
			Model model, 
			RedirectAttributes ra, 
			HttpSession session, SessionStatus status,
            RedirectAttributes redirectAtt
			) {
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			status.setComplete();
			return "index";
		}
		Object obj = model.getAttribute("LoginOK");
		
//        MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//        MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");//		memberBean = null;     // 此敘述測試用
		if (obj == null) {
			status.setComplete();
            redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/_02_ShoppingSystem/ShowCartContent";		}
		if (cmd.equalsIgnoreCase("DEL")) {
	        sc.deleteBook(ComId); // 刪除購物車內的某項商品
		    return SHOW_CART_CONTENT;
		} else if (cmd.equalsIgnoreCase("MOD")) {
			sc.modifyQty(ComId, newQty);   // 修改某項商品的數項
		    return SHOW_CART_CONTENT;
		} else {
			return SHOW_CART_CONTENT;
		}

	}
}
