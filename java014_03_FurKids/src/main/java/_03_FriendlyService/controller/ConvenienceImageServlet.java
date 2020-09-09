package _03_FriendlyService.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.service.ConvenienceService;
import _03_FriendlyService.service.impl.ConvenienceHibernateServiceImpl;


@WebServlet("/_03_/FriendlyService/getBusChildImage")
public class ConvenienceImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		String fileName = null;
		String mimeType = null;
		Blob blob = null;
		try {
			// 讀取瀏覽器傳送來的主鍵
			String busChildNo = request.getParameter("busChildNo");
			// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
			ConvenienceService cs = new ConvenienceHibernateServiceImpl();
			int nNo = 0;
			try {
				nNo = Integer.parseInt(busChildNo);				
			} catch(NumberFormatException ex) {
				;
			}
			MerchantChildBean bean = cs.getBusChild(nNo);
			if (bean != null) {
				blob = bean.getBusChildPhoto();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getBusChildFileName();
			}
			if (is == null) {
				fileName = "NoImage.jpg" ; 
				is = getServletContext().getResourceAsStream(
						"/resources/images/" + fileName);
			}
			mimeType = getServletContext().getMimeType(fileName);
			response.setContentType(mimeType);
			os = response.getOutputStream();
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("_03_FriendlyService.controller.ConvenienceImageServlet#doGet()發生SQLException: " + ex.getMessage());
		} finally{
			if (is != null) is.close();
			if (os != null) os.close();
			
		}
			
			
	}

}
