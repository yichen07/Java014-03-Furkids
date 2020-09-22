package _01_Member.Registration.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_Init.util.GlobalService;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.Impl.MemberServiceImpl;


@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_Member/Registration/Pet")
public class PetRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 
		String contextPath = request.getContextPath();
		
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); 		// 顯示錯誤訊息
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		session.setAttribute("MsgOK", msgOK); 			// 顯示正常訊息
		
		String cusAccount = "";
		
		// Session為null表示使用者尚未登入
//		if (session == null) {
		MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
		if (mb == null) {
			// 請使用者(會員)登入
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/_02_login/login.jsp"));  // "/_02_login/login.jsp"需更改
			errorMsg.put("errorNotLogin", "請先登入會員帳號");
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/PetRegistration.jsp"); // 導向位址需更改
//			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp"); // 導向位址需更改
			rd.forward(request, response);
			return;
		} else {
			// 取得登入資訊
//			MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");
			cusAccount = mb.getCusAccount();
		}
		
		Integer petID = null;
		
		String petName = "";
		
		String petGender = "";
		
		String bDay = "";
		Date petBirthday = null;
		
		String petBreed = "";
		
		String petVariety = "";
		
		Blob petPhoto = null;
		String petFileName = "";

		long sizeInBytes = 0;
		InputStream is = null;
		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();

		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

		// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("petName")) {
						petName = value;
					} else if (fldName.equals("petGender")) {
						petGender = value;
					} else if (fldName.equals("bDay")) {
						bDay = value;
					} else if (fldName.equals("petBreed")) {
						petBreed = value;
					} else if (fldName.equals("petVariety")) {
						petVariety = value;
					} 
				} else {
					// 取出圖片檔的檔名
					petFileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					petFileName = GlobalService.adjustFileName(petFileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (petFileName != null && petFileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					} else {
//						errorMsg.put("errPicture", "必須挑選圖片檔");
					}
				}
			}
			
	// 2. 進行必要的資料轉換
			// (無)
			
	// 3. 檢查使用者輸入資料
			if (petName == null || petName.trim().length() == 0) {
				errorMsg.put("errorNameEmpty", "寵物暱稱必須輸入");
			}
			if (petBreed == null || petBreed.trim().length() == 0) {
				errorMsg.put("errorBreedEmpty", "寵物品種必須輸入");
			}
			if (petVariety == null || petVariety.trim().length() == 0) {
				errorMsg.put("errorVarietyEmpty", "寵物種類必須輸入");
			}
			
			// 檢查使用者所輸入的資料(生日)
			if (bDay != null && bDay.trim().length() > 0) {
				try {
					petBirthday = java.sql.Date.valueOf(bDay);
				} catch (IllegalArgumentException e) {
					errorMsg.put("errorDayFormat", "寵物生日欄格式錯誤");
				}
			}
		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		
		
		// 如果有錯誤訊息產生
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，並顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/PetRegistration.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			// 4. 產生MemberDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存會員的資料
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//			MemberService service = new MemberServiceImpl();
			MemberService service = ctx.getBean(MemberService.class);

//			if (service.accountExists(cusAccount)) {
//				errorMsg.put("errorAccountDup", "此帳號已存在，請換新帳號");
//			} else {
//				// 為了配合Hibernate的版本。
//				// 要在此加密，不要在 dao.saveMember(mem)進行加密
//				cusPassword = GlobalService.getMD5Endocing(GlobalService.encryptString(cusPassword));
////				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis()); // 當下註冊時間。
				
			petBirthday = java.sql.Date.valueOf(bDay);
			
			if (is != null) {
				petPhoto = GlobalService.fileToBlob(is, sizeInBytes);
			}
				
			// 將所有寵物資料封裝到PetMemberBean(類別的)物件
			PetBean pet = new PetBean(petID, cusAccount, petName, petGender, petBirthday, petBreed, petVariety, petPhoto, petFileName);
			// 呼叫MemberDao的saveMember方法
			int n = service.savePet(pet);
			if (n == 1) {
				msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
				response.sendRedirect(contextPath + "/_01_Member/PetRegistration.jsp");
				return;
			} else {
				errorMsg.put("errorIdDup", "新增此筆資料有誤(PetRegisterServlet)");
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/PetRegistration.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIdDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/PetRegistration.jsp");
			rd.forward(request, response);
		}

	}

}
