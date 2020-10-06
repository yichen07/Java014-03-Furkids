package _04_Community.PetColumn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_Community.PetColumn.model.PetColumnBean;
import _04_Community.PetColumn.service.PetColumnService;
import _04_Community.PetColumn.service.impl.PetColumnServiceImpl;

@WebServlet("/_04_Community/BlogArtical")
public class BlogArtical extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			String pcid = request.getParameter("PCID");
			PetColumnService pcs = new PetColumnServiceImpl();
			PetColumnBean pcb = pcs.getPetColumn(pcid);
			pcs.updatePCViews(pcb.getPCID(), pcb.getPCViews());
			session.setAttribute("pcb", pcb);
			List<PetColumnBean> pcbList = pcs.selectBlogArticalAll(pcid);
			session.setAttribute("pcbList", pcbList);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_Community/blogArtical.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
