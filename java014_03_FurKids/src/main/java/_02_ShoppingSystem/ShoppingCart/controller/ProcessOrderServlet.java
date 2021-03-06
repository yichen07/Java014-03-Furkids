package _02_ShoppingSystem.ShoppingCart.controller;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
//import _01_register.model.MemberBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;
import _02_ShoppingSystem.ShoppingCart.service.OrderService;
import _02_ShoppingSystem.ShoppingCart.service.Impl.OrderServiceImpl;

// OrderConfirm.jsp 呼叫本程式。
@WebServlet("/_02_ShoppingCart/ProcessOrder.do")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String finalDecision = request.getParameter("finalDecision");		
		HttpSession session = request.getSession(false);
		if (session == null) {   // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		Object obj = session.getAttribute("LoginOK");
		String memberId = null;
//		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		if (obj == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}else if(obj instanceof MemberBean) {
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
			memberId = mb.getCusAccount();
		}else {
			MerchantBean mcb = (MerchantBean) session.getAttribute("LoginOK");
			memberId = mcb.getBusAccount();
		}
		
		
		
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		// 如果使用者取消訂單
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			return;  			// 一定要記得 return 
		}
//		String memberId = mb.getCusAccount();     // 取出會員代號
		double totalAmount = Math.round(sc.getSubtotal() * 1.0);  	// 計算訂單總金額 
		String shippingAddress = request.getParameter("ShippingAddress");  // 出貨地址
		Date today = new Date();   									// 新增訂單的時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
		OrderBean ob = new OrderBean(null, memberId , today, totalAmount, shippingAddress, null);
		
		// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean，
		
		Map<Integer, OrderListBean> content = sc.getContent();
		
		Set<OrderListBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for(Integer i : set) {
			items.add(content.get(i));
		}
		
		// 執行到此，購物車內所有購買的商品已經全部轉換為為OrderItemBean物件，並放在Items內
		ob.setItems(items);  
		try {
			OrderService orderService = new OrderServiceImpl();
			orderService.persistOrder(ob);
			session.removeAttribute("ShoppingCart");
//			response.sendRedirect(response.encodeRedirectURL ("/java014_03_FurKids/_02_ShoppingSystem/ThanksForOrdering.jsp"));
				response.sendRedirect(getServletContext().getContextPath()
						+ "/index.jsp");
				return;

		} catch(RuntimeException ex){
			String message = ex.getMessage();
			String shortMsg = "" ;   
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			session.setAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			response.sendRedirect(response.encodeRedirectURL ("/_02_ShoppingSystem/ShowCartContent.jsp"));
			return;
		}
	}
}