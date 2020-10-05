package _04_Community.PetColumn.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_Init.util.GlobalService;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _04_Community.PetColumn.model.PetColumnBean;
import _04_Community.PetColumn.service.PetColumnService;
import _04_Community.PetColumn.service.impl.PetColumnServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_04_Community/SaveBlogIndex")
public class SaveBlogIndex extends HttpServlet {
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

			// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
			pcb.setPCTitle(request.getParameter("blogTitle"));
			pcb.setPCContent(request.getParameter("blogContent"));
			Part blogImg = request.getPart("blogImg");
			InputStream is = blogImg.getInputStream();
			if (is != null)
				pcb.setPCImage(GlobalService.fileToBlob(is, blogImg.getSize()));
			PetColumnService pcs = new PetColumnServiceImpl();
			pcs.saveBlogIndex(pcb);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_Community/BlogIndex");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
