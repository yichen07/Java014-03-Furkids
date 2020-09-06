package _03_FriendlyService.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_Init.util.utils.HibernateUtils;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.service.impl.ConvenienceHibernateServiceImpl;


@WebServlet("/_03_ConvenienceProcess/Convenience_H.do")
public class ConvenienceServlet_H extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//		MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
//		mb.getMemberId()
		
		String userId = "pilimou";


		ConvenienceService service = new ConvenienceHibernateServiceImpl();
		List<ConvenienceBean_H> cb = service.getAllConvenience(userId);
		List<MerchantChildBean> mb = service.getNotConvenience(userId);
		
		request.setAttribute("AllConvenience", cb);
		request.setAttribute("NotConvenience", mb);
		RequestDispatcher rd = request.getRequestDispatcher("/_03_FriendlySystem/convenience.jsp");
		rd.forward(request, response);
		return;
	}

}
