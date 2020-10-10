package _02_ShoppingSystem.CommodityList.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;

// 當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
@Controller
//@WebServlet("/_02_ShoppingSystem/BuyCommodity.do")
@RequestMapping("_02_ShoppingSystem")
@SessionAttributes({ "LoginOK", "pageNo", "products_DPP", "ShoppingCart", "loginBean"})
public class BuyItemController {

	@Autowired
	ServletContext context;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/BuyCommodity.do")
	protected String buyBook(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Object obj = model.getAttribute("LoginOK");
	
		HttpSession session = request.getSession(false); 

//		if (session == null) {
//			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/_02_login/login.jsp"));
//			return;
//		}
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
//		// 如果找不到ShoppingCart物件
		if (cart == null) {
//			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
//			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			model.addAttribute("ShoppingCart", cart);   
		}
		String ComIdStr 	= request.getParameter("ComId");
		int ComId          = Integer.parseInt(ComIdStr.trim());
		
		String qtyStr 		= request.getParameter("qty");
		Integer qty = 0 ; 

		Map<Integer, CommodityBean> CommodityMap = (Map<Integer, CommodityBean>) session.getAttribute("products_DPP");
		CommodityBean bean = CommodityMap.get(ComId);
		String pageNo 		= request.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0){
			pageNo = (String) model.getAttribute("pageNo") ;
			if (pageNo == null){
			   pageNo = "1";
			} 
		} 
		
		try{
			// 進行資料型態的轉換
			qty = Integer.parseInt(qtyStr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
//		// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
		OrderListBean oib = new  OrderListBean(null, ComId, bean.getComName(), 
				qty, bean.getComPrice(),bean.getComValidity(), bean.getMerchantBean().getBusName());
//		// 將OrderItem物件內加入ShoppingCart的物件內
		cart.addToCart(ComId, oib);
		return  "_02_ShoppingSystem/shoppinglist";
	}
}