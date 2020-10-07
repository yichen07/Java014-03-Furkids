package _02_ShoppingSystem.ShoppingCart.controller;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;
import _02_ShoppingSystem.ShoppingCart.service.OrderService;


// OrderConfirm.jsp 呼叫本程式。
//@WebServlet("/_02_ShoppingCart/ProcessOrder.do")
@Controller
@RequestMapping("_02_ShoppingSystem")
@SessionAttributes({ "LoginOK", "ShoppingCart", "OrderErrorMessage" ,"loginBean"})
public class ProcessOrderController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	OrderService orderService;

	@PostMapping("ProcessOrder")
    protected String processOrder(Model model,
            @RequestParam("ShippongAddress") String shippingAddress,
            WebRequest webRequest, SessionStatus status,			
            RedirectAttributes redirectAtt
			) {
		String memberId = null;
	    MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");	   
        MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
        if (memberBean == null && merchantBean == null){
 			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/_01_Member/PetRegistration";
       } else if(memberBean != null){
            memberId = memberBean.getCusAccount();   						// 取出會員代號
       } else {
            memberId = merchantBean.getBusAccount();   						// 取出會員代號

       }
		
		
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			return "index";
		}
		// 如果使用者取消訂單
		

		double totalAmount = Math.round(sc.getSubtotal() * 1.0);  	// 計算訂單總金額 
		//String shippingAddress = request.getParameter("ShippingAddress");  // 出貨地址
		Date today = new Date();   									// 新增訂單的時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
		OrderBean ob = new OrderBean(null, memberId , today, totalAmount, shippingAddress, null);
		
		// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean，
		
		Map<Integer, OrderListBean> content = sc.getContent();
		
		Set<OrderListBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for(Integer i : set) {
			OrderListBean oib = content.get(i);
          	oib.setOrderBean(ob);
			items.add(oib);					     
		}
		
		// 執行到此，購物車內所有購買的商品已經全部轉換為為OrderItemBean物件，並放在Items內
		ob.setItems(items);  
		try {

			orderService.persistOrder(ob);
//			response.sendRedirect(response.encodeRedirectURL ("/java014_03_FurKids/_02_ShoppingSystem/ThanksForOrdering.jsp"));
			System.out.println("Order Process OK");
			return "forward:" + "removeShoppingCart";

		} catch(RuntimeException ex){
			String message = ex.getMessage();
			String shortMsg = "" ;   
			System.out.println("message=" + message);
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			model.addAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			return "redirect:/_02_ShoppingSystem/ShowCartContent";

		}
	}
}