package _03_FriendlyService.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_FriendlyService.model.ConvenienceBean;
import _03_FriendlyService.service.impl.ConvenienceServiceImpl;


@WebServlet("/_03_ConvenienceProcess/Convenience.do")
public class ConvenienceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//		MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
//		mb.getMemberId()
		String userId = "pilimou";
		ConvenienceServiceImpl service = new ConvenienceServiceImpl();
		Map<Integer, ConvenienceBean> cb = service.getConvenience(userId);
		request.setAttribute("AllConvenience", cb);
		RequestDispatcher rd = request.getRequestDispatcher("/_03_FriendlySystem/test.jsp");
		rd.forward(request, response);
		return;
	}

}
