package _02_ShoppingSystem.ShoppingCart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import _02_ShoppingSystem.ShoppingCart.model.ShoppingCart;

@WebServlet("/_04_ShoppingCart/checkout.do")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		
		
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); 	// 顯示錯誤訊息
		
		Object obj = session.getAttribute("LoginOK");
		
		if (obj == null) {
			// 請使用者登入
			errorMsg.put("errorNotLogin", "請先登入帳號");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_ShoppingSystem/ShowCartContent.jsp"); // 導向位址需更改
			rd.forward(request, response);
			return;
		}
		
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath()
					+ "/index.jsp");
			return;
		}
		// 結帳
		RequestDispatcher rd = request.getRequestDispatcher("/_02_ShoppingSystem/OrderConfirm.jsp");
		rd.forward(request, response);
		return;
	}
}