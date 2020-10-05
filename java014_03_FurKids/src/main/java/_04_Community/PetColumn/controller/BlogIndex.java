package _04_Community.PetColumn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/_04_Community/BlogIndex")
public class BlogIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			PetColumnService pcs = new PetColumnServiceImpl();
			List<PetColumnBean> pcbList = pcs.selectBlogIndexAll();
			session.setAttribute("pcbList", pcbList);
			Map<String, Integer> PCNumberOfMessages = new HashMap<>();
			for (PetColumnBean pcb : pcbList) {
				int pcfid = pcs.getpcfidCOUNT(pcb.getPCID());
				PCNumberOfMessages.put(pcb.getPCID(), pcfid);
			}
			session.setAttribute("PCNumberOfMessages", PCNumberOfMessages);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_Community/blogIndex.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
