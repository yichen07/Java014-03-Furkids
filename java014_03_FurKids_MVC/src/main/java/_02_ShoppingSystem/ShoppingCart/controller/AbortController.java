package _02_ShoppingSystem.ShoppingCart.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;



// 當進行『結帳』時，如果按下『放棄購物』超連結，瀏覽器會要求此程式

//@WebServlet("/_04_ShoppingCart/abort.do")
@Controller
@RequestMapping("_02_ShoppingSystem")
@SessionAttributes({"ShoppingCart" , "loginBean"})
public class AbortController{
	@Autowired
    ServletContext context;
    
    @GetMapping("abort")
	protected String abort(HttpSession session, Model model, WebRequest webRequest, SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
