package _03_FriendlyService.controller;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/_03_ConvenienceProcess/Convenience_H.do")
public class ConvenienceServlet_H extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/index.jsp"));
			return;
		}
		
		
		
		MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");			
		
		
		
		String userId = mb.getBusAccount();


//		ConvenienceService service = new ConvenienceHibernateServiceImpl();
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ConvenienceService service = ctx.getBean(ConvenienceService.class);
		
		List<ConvenienceBean_H> cb = service.getAllConvenience(userId);
		List<MerchantChildBean> mcb = service.getNotConvenience(userId);
		
		request.setAttribute("AllConvenience", cb);
		request.setAttribute("NotConvenience", mcb);
		RequestDispatcher rd = request.getRequestDispatcher("/_03_FriendlySystem/convenience.jsp");
		rd.forward(request, response);
		return;
	}

}
