package _02_ShoppingSystem.CommodityList.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _02_ShoppingSystem.CommodityList.model.CommodityBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;

// 當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
@WebServlet("/_02_ShoppingSystem/BuyCommodity.do")
public class BuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 

//		if (session == null) {
//			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/_02_login/login.jsp"));
//			return;
//		}
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
//		// 如果找不到ShoppingCart物件
		if (cart == null) {
//			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
//			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			session.setAttribute("ShoppingCart", cart);   
		}
		String ComIdStr 	= request.getParameter("ComId");
		int ComId          = Integer.parseInt(ComIdStr.trim());
		
		String qtyStr 		= request.getParameter("qty");
		Integer qty = 0 ; 

		Map<Integer, CommodityBean> CommodityMap = (Map<Integer, CommodityBean>) session.getAttribute("products_DPP");
		CommodityBean bean = CommodityMap.get(ComId);
		String pageNo 		= request.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0){
			pageNo = (String) session.getAttribute("pageNo") ;
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
		OrderListBean oib = new  OrderListBean(null, null, ComId, bean.getComName(), 
				qty, bean.getComPrice(),bean.getComValidity(), bean.getBusName());
//		// 將OrderItem物件內加入ShoppingCart的物件內
		cart.addToCart(ComId, oib);
		RequestDispatcher rd = request.getRequestDispatcher("/_02_ShoppingSystem/DisplayPageProducts?pageNo=" + pageNo);
		rd.forward(request, response);
	}
}