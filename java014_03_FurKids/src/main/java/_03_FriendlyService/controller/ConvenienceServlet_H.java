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
	       doPost(request, response);
		}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		MerchantBean mb = null;
		String userId;
		if(session == null || (MerchantBean)session.getAttribute("LoginOK") == null) {		
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/index.jsp"));
			return;		
		} else {
			 mb = (MerchantBean)session.getAttribute("LoginOK");			
			userId = mb.getBusAccount();			
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
		
//		ConvenienceService service = new ConvenienceHibernateServiceImpl();
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ConvenienceService service = ctx.getBean(ConvenienceService.class);
		
//		List<ConvenienceBean_H> cb = service.getAllConvenience(userId);
		List<ConvenienceBean_H> cb = service.getPageConvenience(userId, pageNo);
		List<MerchantChildBean> mcb = service.getNotConvenience(userId);
		
		request.setAttribute("AllConvenience", cb);
		//未上架的分店
		request.setAttribute("NotConvenience", mcb);
		//總頁數
		int n = service.getTotalPages();
		//如果已上架分店是八的倍數且還有未上架的分店
		if(cb.size() % 8 == 0 && mcb.size() > 0) {
			n++;
		}
		request.setAttribute("TotalPages", n);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_03_FriendlySystem/convenience.jsp");
		rd.forward(request, response);
		return;
	}

}
