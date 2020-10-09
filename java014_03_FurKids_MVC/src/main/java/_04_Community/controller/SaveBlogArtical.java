package _04_Community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _04_Community.model.PetColumnBean;
import _04_Community.service.PetColumnService;
import _04_Community.service.impl.PetColumnServiceImpl;

@WebServlet("/_04_Community/SaveBlogArtical")
public class SaveBlogArtical extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			PetColumnBean pcb = new PetColumnBean();
			if (session.getAttribute("LoginOK") instanceof MerchantBean) {
				MerchantBean merchantBean = (MerchantBean) session.getAttribute("LoginOK");
				pcb.setPCFounder(merchantBean.getBusName());
				pcb.setPCAccount(merchantBean.getBusAccount());
			} else if (session.getAttribute("LoginOK") instanceof MemberBean) {
				MemberBean memberBean = (MemberBean) session.getAttribute("LoginOK");
				pcb.setPCFounder(memberBean.getCusName());
				pcb.setPCAccount(memberBean.getCusAccount());
			}
			pcb.setPCContent(request.getParameter("blogContent"));
			pcb.setPCFID(((PetColumnBean) session.getAttribute("pcb")).getPCID());

			PetColumnService pcs = new PetColumnServiceImpl();
			pcs.saveBlogArtical(pcb);
			RequestDispatcher rd = request.getRequestDispatcher(
					"/_04_Community/BlogArtical?PCID=" + ((PetColumnBean) session.getAttribute("pcb")).getPCID());
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
