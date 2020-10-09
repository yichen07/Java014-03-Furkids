package _04_Community.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _04_Community.model.PetColumnBean;
import _04_Community.service.PetColumnService;
import _04_Community.service.impl.PetColumnServiceImpl;

@WebServlet("/_04_Community/getPCImage")
public class GetPCImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		Blob PCImage = null;
		try {
			String pcid = request.getParameter("PCID");
			PetColumnService pcs = new PetColumnServiceImpl();
			PetColumnBean pcb = pcs.getPetColumn(pcid);
			PCImage = pcb.getPCImage();
			if (PCImage != null) 
				is = PCImage.getBinaryStream();
			// 設定輸出資料的MIME型態
			response.setContentType(getServletContext().getMimeType(pcb.getPCTitle()));
			// 取得能寫出非文字資料的OutputStream物件
			os = response.getOutputStream();
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
			os.close();
		}

	}
}
