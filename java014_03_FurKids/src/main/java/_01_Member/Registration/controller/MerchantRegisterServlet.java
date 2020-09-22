package _01_Member.Registration.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.MerchantService;
import _01_Member.Registration.service.Impl.MemberServiceImpl;
import _01_Member.Registration.service.Impl.MerchantServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_Member/Registration/Merchant")
public class MerchantRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 設定密碼欄位必須由大寫字母、小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於八個字元
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		request.setAttribute("MsgMap", errorMsg); 	// 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); 		// 顯示正常訊息

		String busAccount = "";
		String busPassword = "";
		String confirmPassword = "";
		String busName = "";
		String busEmail = "";
		String busTel = "";
		String busAddress = "";
		String busDescription = "";			
		Blob busPhoto = null;
		String busFileName = "";
		
		
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
					if (fldName.equals("busAccount")) {
						busAccount = value;
					} else if (fldName.equals("busPassword")) {
						busPassword = value;
					} else if (fldName.equals("confirmPassword")) {
						confirmPassword = value;
					} else if (fldName.equals("busName")) {
						busName = value;
					} else if (fldName.equals("busEmail")) {
						busEmail = value;
					} else if (fldName.equals("busTel")) {
						busTel = value;
					} else if (fldName.equals("busAddress")) {
						busAddress = value;
					} else if (fldName.equals("busDescription")) {
						busDescription = value;
					}
				} else {
					// 取出圖片檔的檔名
					busFileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					busFileName = GlobalService.adjustFileName(busFileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (busFileName != null && busFileName.trim().length() > 0) {
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
			if (busAccount == null || busAccount.trim().length() == 0) {
				errorMsg.put("errorIdEmpty", "帳號欄必須輸入");
			}
			if (busPassword == null || busPassword.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
			}
			if (confirmPassword == null || confirmPassword.trim().length() == 0) {
				errorMsg.put("errorPassword1Empty", "密碼確認欄必須輸入");
			}
			if (busPassword.trim().length() > 0 && confirmPassword.trim().length() > 0) {
				if (!busPassword.trim().equals(confirmPassword.trim())) {
					errorMsg.put("errorPassword1Empty", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}
			
			if (busName == null || busName.trim().length() == 0) {
				errorMsg.put("errorName", "商店名欄必須輸入");
			}
			
			if (busEmail == null || busEmail.trim().length() == 0) {
					errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			}
			
			if (busTel == null || busTel.trim().length() == 0) {
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			}
			if (busAddress == null || busAddress.trim().length() == 0) {
				errorMsg.put("errorAddr", "地址欄必須輸入");
			}
		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		
		// 如果密碼輸入格式有錯誤
		if (errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(busPassword);
			if (!matcher.matches()) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
			}
		}
		// 如果有錯誤訊息產生
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，並顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantRegistration.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			// 4. 產生MemberDao與MerchantDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc與MerchantDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存商家的資料
			
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//			MemberService service = new MemberServiceImpl();
			MemberService memberService = ctx.getBean(MemberService.class);
//			MerchantService service2 = new MerchantServiceImpl();
			MerchantService merchantService = ctx.getBean(MerchantService.class);

			if (memberService.accountExists(busAccount) || merchantService.accountExists(busAccount)) {
				errorMsg.put("errorAccountDup", "此帳號已存在，請換新帳號");
			} else {
				// 為了配合Hibernate的版本。
				// 要在此加密，不要在 dao.saveMember(mem)進行加密
				busPassword = GlobalService.getMD5Endocing(GlobalService.encryptString(busPassword));
//				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis()); // 當下註冊時間。
//				cusBirthday = java.sql.Date.valueOf(bDay);
				
				if (is != null) {
					busPhoto = GlobalService.fileToBlob(is, sizeInBytes);
				}
				// 將所有會員資料封裝到MerchantBean與MerchantChildBean(類別的)與物件
				MerchantBean mb = new MerchantBean(busAccount, busPassword, busName, busEmail, busTel, busAddress, busDescription, busPhoto, busFileName);
				// 呼叫MemberDao的saveMember方法
				int n = merchantService.saveMerchant(mb);

				if (n == 1) {
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					response.sendRedirect(contextPath + "/index.jsp");
					return;
				} else {
					errorMsg.put("errorIdDup", "新增此筆資料有誤(MerchantRegisterServlet)");
				}
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantRegistration.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIdDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Member/MerchantRegistration.jsp");
			rd.forward(request, response);
		}

	}

}
