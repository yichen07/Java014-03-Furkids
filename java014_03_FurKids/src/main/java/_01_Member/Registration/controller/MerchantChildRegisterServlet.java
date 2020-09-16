package _01_Member.Registration.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MerchantService;
import _01_Member.Registration.service.Impl.MerchantServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_Member/Registration/MerchantChild")
public class MerchantChildRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 設定密碼欄位必須由大寫字母、小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於八個字元
//	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
//	private Pattern pattern = null;
//	private Matcher matcher = null;

	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); 	// 顯示錯誤訊息
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		session.setAttribute("MsgOK", msgOK); 		// 顯示正常訊息
		

		String busAccount = "";
		
		// Session為null表示使用者尚未登入
//		if (session == null) {
		MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");
		if (mb == null) {
			// 請使用者(商家)登入
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/_02_login/login.jsp"));  // "/_02_login/login.jsp"需更改
			errorMsg.put("errorNotLogin", "請先登入帳號");
//			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantChildRegistration.jsp"); // 導向位址需更改
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp"); // 導向位址需更改
			rd.forward(request, response);
			return;
		} else {
			// 取得登入資訊
//			MerchantBean mb = (MerchantBean)session.getAttribute("LoginOK");
			busAccount = mb.getBusAccount();
		}
		
		
		Integer busChildNo = null;
		// 使用者須提供的資料
		String busChildName = "";
		String busChildTel = "";
		String busChildAddress = "";
		String busChildDescription = "";
		Blob busChildPhoto = null;
		String busChildFileName = "";
		
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
					if (fldName.equals("busChildName")) {
						busChildName = value;
					} else if (fldName.equals("busChildTel")) {
						busChildTel = value;
					} else if (fldName.equals("busChildAddress")) {
						busChildAddress = value;
					} else if (fldName.equals("busChildDescription")) {
						busChildDescription = value;
					} 
				} else {
					// 取出圖片檔的檔名
					busChildFileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					busChildFileName = GlobalService.adjustFileName(busChildFileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (busChildFileName != null && busChildFileName.trim().length() > 0) {
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
			if (busChildName == null || busChildName.trim().length() == 0) {
				errorMsg.put("errorName", "商家分店名稱欄必須輸入");
			}
			if (busChildTel == null || busChildTel.trim().length() == 0) {
				errorMsg.put("errorTel", "商家電話欄必須輸入");
			}
			if (busChildAddress == null || busChildAddress.trim().length() == 0) {
				errorMsg.put("errorAddr", "商家分店地址欄必須輸入");
			}
		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		
		// 如果有錯誤訊息產生
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，並顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantChildRegistration.jsp"); // 導向位址需更改
			rd.forward(request, response);
			return;
		}
		
		try {
			// 4. 產生MemberDao與MerchantDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc與MerchantDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存商家的資料
			
			MerchantService service = new MerchantServiceImpl();
			
			if (service.merchantChildExists(busAccount, busChildAddress)) {
				errorMsg.put("errorMerchantChildDup", "此分店地址已存在，請確認");
			} else {
				if (is != null) {
					busChildPhoto = GlobalService.fileToBlob(is, sizeInBytes);
				}
				// 將所有會員資料封裝到MerchantBean與MerchantChildBean(類別的)與物件
				MerchantChildBean mcb = new MerchantChildBean(busAccount, busChildNo, busChildName, busChildTel, busChildAddress, busChildDescription, busChildPhoto, busChildFileName);
				// 呼叫MemberDao的saveMember方法
				int n = service.saveMerchantChild(mcb);

				if (n == 1) {
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					response.sendRedirect("/java014_03_FurKids/_01_Member/SuccessRegistration.jsp");
					return;
				} else {
					errorMsg.put("errorIdDup", "新增此筆資料有誤(MerchantChildRegisterServlet)");
				}
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchanChildtRegistration.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIdDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantChildRegistration.jsp");
			rd.forward(request, response);
		}

	}

}
