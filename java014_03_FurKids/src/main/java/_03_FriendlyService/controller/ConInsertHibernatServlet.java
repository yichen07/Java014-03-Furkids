package _03_FriendlyService.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.service.impl.ConvenienceHibernateServiceImpl;

@WebServlet("/_03_ConvenienceProcess/ConInsert.do")
public class ConInsertHibernatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");			
		
		String accountId = mb.getBusAccount();
		
		
			  
		String busChildNo = request.getParameter("busChildNo");
		String convenience = request.getParameter("convenience");
		String convenienceList = request.getParameter("conveniencelist");
		String condCloseDay = request.getParameter("concloseday");
		String conOpenTime = request.getParameter("conopentime");
		String conCloseTime = request.getParameter("conclosetime");
		String busChildDescription = request.getParameter("buschilddescription");

		Integer busNo = Integer.parseInt(busChildNo); 
		System.out.println(convenience + "," + busNo + "," + convenienceList + "," + condCloseDay + "," + conOpenTime + "," + conCloseTime);
		ConvenienceBean_H cbh = new ConvenienceBean_H(busNo, accountId,convenience,convenienceList,condCloseDay,conOpenTime,conCloseTime);
		
		
		 
		
		ConvenienceService server = new ConvenienceHibernateServiceImpl();
		server.insert(cbh);
		//用傳回來的分店編號(主鍵)去抓該筆分店資料
		MerchantChildBean bean = server.getBusChild(busNo);
		bean.setBusChildDescription(busChildDescription);
		server.update(bean);

		response.sendRedirect(response.encodeRedirectURL("Convenience_H.do"));
	
	}

}
