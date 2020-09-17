package _03_FriendlyService.controller;

import java.io.IOException;

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


@WebServlet("/_03_ConvenienceProcess/ConRevise.do")
public class ConReviseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/index.jsp"));
			return;
		}
		MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");
		
		
		String reviseno = request.getParameter("reviseno");
		String convenience = request.getParameter("convenience");
		String convenienceList = request.getParameter("conveniencelist");
		
		String busChildAddress = request.getParameter("busChildAddress");
		
		String condCloseDay = request.getParameter("concloseday");
		String conOpenTime = request.getParameter("conopentime");
		String conCloseTime = request.getParameter("conclosetime");
		
		String busEmail = request.getParameter("busEmail");
		
		String busChildTel = request.getParameter("busChildTel");
		
		String busChildDescription = request.getParameter("buschilddescription");
		
		int no = Integer.parseInt(reviseno);
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ConvenienceService service = ctx.getBean(ConvenienceService.class);
		
		ConvenienceBean_H cbh = service.getConvenience(no);
		cbh.setConItem(convenience);
		cbh.setConItemList(convenienceList);
		cbh.setConCloseDay(condCloseDay);
		cbh.setConOpenTime(conOpenTime);
		cbh.setConCloseTime(conCloseTime);
		
		MerchantChildBean mcb = service.getBusChild(no);
		mcb.setBusChildTel(busChildTel);
		mcb.setBusChildAddress(busChildAddress);
		mcb.setBusChildDescription(busChildDescription);
		
		
		
		mb.setBusEmail(busEmail);
		
		service.Update(cbh, mcb, mb);
		
		response.sendRedirect(response.encodeRedirectURL("Convenience_H.do"));
	}

}
