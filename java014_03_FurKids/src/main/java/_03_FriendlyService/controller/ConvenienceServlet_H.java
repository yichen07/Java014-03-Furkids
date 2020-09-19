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
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ConvenienceService service = ctx.getBean(ConvenienceService.class);
		
		//如果沒有分店就導到分店註冊
		List<MerchantChildBean> nocb = service.getBusChild(userId);		
			if( !(nocb.size() > 0) ) {
				String noBusChild = "目前尚無分店";
				session.setAttribute("noBusChild", noBusChild);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/_01_Member/MerchantChildRegistration.jsp"));
				return;
			}
		
		//抓傳進來的頁數，如果沒抓到，就從session抓，如果還是沒有，就給預設值第一頁
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().length() == 0){
			pageNoStr =  (String)session.getAttribute("nowPage") ;
			if (pageNoStr == null){
			   pageNoStr = "1";
			} 
		} 
		//把上面結果的頁數存進session
		session.setAttribute("nowPage", pageNoStr);
		int pageNo = Integer.parseInt(pageNoStr);
		
		

		//總上架分店
		List<ConvenienceBean_H> allcb = service.getAllConvenience(userId);  
		//限制一次只抓8筆，從頁數去選擇要從哪筆開始抓
		List<ConvenienceBean_H> cb = service.getPageConvenience(userId, pageNo); 
		//尚未上架的商家分店
		List<MerchantChildBean> mcb = service.getNotConvenience(userId);
		
		//總頁數
		int n = service.getTotalPages(userId);
	
		//如果總上架的分店數是八的倍數且還有未上架的分店
		if(allcb.size() % 8 == 0 && mcb.size() > 0) {
			n=n+1;
		}
		
		request.setAttribute("AllConvenience", cb);
		request.setAttribute("NotConvenience", mcb);
		request.setAttribute("TotalPages", n);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_03_FriendlySystem/convenience.jsp?pageNo="+pageNo);
		rd.forward(request, response);
		return;
	}

}
