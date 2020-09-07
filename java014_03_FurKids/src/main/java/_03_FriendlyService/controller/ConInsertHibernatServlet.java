package _03_FriendlyService.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.service.impl.ConvenienceHibernateServiceImpl;

@WebServlet("/_03_ConvenienceProcess/ConInsert.do")
public class ConInsertHibernatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String accountId = "pilimou";
		String convenience = request.getParameter("convenience");
		String busChildNo = request.getParameter("busChildNo");
		String convenienceList = request.getParameter("conveniencelist");
		String condCloseDay = request.getParameter("concloseday");
		String conOpenTime = request.getParameter("conopentime");
		String conCloseTime = request.getParameter("conclosetime");
		
		Integer busNo = Integer.parseInt(busChildNo); 
		System.out.println(convenience + "," + busNo + "," + convenienceList + "," + condCloseDay + "," + conOpenTime + "," + conCloseTime);
		ConvenienceBean_H cbh = new ConvenienceBean_H(busNo, accountId,convenience,convenienceList,condCloseDay,conOpenTime,conCloseTime);
		ConvenienceService server = new ConvenienceHibernateServiceImpl();
		server.insert(cbh);
		
		ConvenienceServlet_H csh = new ConvenienceServlet_H();
		csh.doGet(request, response);
	
	
	}

}
