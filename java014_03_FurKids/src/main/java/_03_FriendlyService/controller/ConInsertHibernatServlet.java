package _03_FriendlyService.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;


@WebServlet("/_03_ConvenienceProcess/ConInsert.do")
public class ConInsertHibernatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       doPost(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/index.jsp"));
			return;
		}
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		
		MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");	
		session.setAttribute("ErrorMsg", errorMsgMap);
		
		
		
		String accountId = mb.getBusAccount();
		
		
			  
		String busChildNo = request.getParameter("busChildNo");
		String convenience = request.getParameter("convenience");
		String convenienceList = request.getParameter("conveniencelist");
		String condCloseDay = request.getParameter("concloseday");
		String conOpenTime = request.getParameter("conopentime");
		String conCloseTime = request.getParameter("conclosetime");
		String busChildDescription = request.getParameter("buschilddescription");

		if (convenience == null || convenience.trim().length() == 0) {
			errorMsgMap.put("ConvenienceError", "服務種類必須輸入");
		}
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ConvenienceService server = ctx.getBean(ConvenienceService.class);
		
		Integer errorNo = Integer.parseInt(busChildNo);
		MerchantChildBean errorMcb = server.getBusChild(errorNo);
		String pilimou = errorMcb.getBusChildName();
		session.setAttribute("pilimou", pilimou);
		System.out.println(pilimou);
		if (!errorMsgMap.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL("Convenience_H.do"));
			return;
		}
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().length() == 0){
			pageNoStr = (String) session.getAttribute("pageNo") ;
			if (pageNoStr == null){
			   pageNoStr = "1";
			} 
		} 
		
		int pageNo = Integer.parseInt(pageNoStr);
		session.setAttribute("nowPage", pageNo);
		
		Integer busNo = Integer.parseInt(busChildNo); 
//		System.out.println(convenience + "," + busNo + "," + convenienceList + "," + condCloseDay + "," + conOpenTime + "," + conCloseTime);
		
//		ConvenienceService server = new ConvenienceHibernateServiceImpl();
		
		//把傳進來要新增的資料放進去
		ConvenienceBean_H cbh = new ConvenienceBean_H(busNo, accountId,convenience,convenienceList,condCloseDay,conOpenTime,conCloseTime);
		//用傳回來的分店編號(主鍵)去抓該筆分店資料
		MerchantChildBean mcb = server.getBusChild(busNo);
		mcb.setBusChildDescription(busChildDescription);
		server.insertAndUpdate(cbh, mcb);	
		response.sendRedirect(response.encodeRedirectURL("Convenience_H.do"));
		return;
	
	}

}
