package _00_Init.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import _00_Init.util.GlobalService;
import _01_Member.Login.model.LoginBean;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.MerchantService;

@Controller
@SessionAttributes("loginBean")
public class BaseController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	@Autowired
	ServletContext servletContext;
	
	
	
// 回首頁
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
// 由Cookie取得使用者登入的資料
	@ModelAttribute("loginBean")
	public LoginBean loginEmptyForm(HttpServletRequest request, Model model, 
		@CookieValue(value="user", required = false) String user, 					// value="user"相當於是Cookie的名稱。
		@CookieValue(value="password", required = false) String password, 
		@CookieValue(value="rm", required = false) Boolean rm 
			) {
		if (user == null)
			user = "";
		if (password == null) {
			password = "";
		} else {
			password = GlobalService.decryptString(GlobalService.KEY, password);
		}
		
		if (rm != null) {
			rm = Boolean.valueOf(rm);
		} else {
			rm = false;
		}
			
		LoginBean loginBean = new LoginBean(user, password, rm);
		model.addAttribute("loginBean", loginBean);	
		return loginBean;
	}
	
// 取得使用者大頭照。
	@GetMapping("/_00_init/getHeadshot")
	public ResponseEntity<byte[]>  getHeadshot(@RequestParam("account") String account) { 	// 送回圖片ResponseEntity<byte[]>；@RequestParam("id") ==> //..../_00_init/getMemberImage/?id=5
//	@GetMapping("/_00_init/getMemberImage/{id}")
//	public ResponseEntity<byte[]>  getMemberImage(@PathVariable("id") String id) { 	// 送回圖片ResponseEntity<byte[]>；@PathVariable("id") ==> //..../_00_init/getMemberImage/5
		InputStream is = null;
		OutputStream os = null;
		String fileName = null;
		String mimeType = null;
		byte[] media = null;
		ResponseEntity<byte[]> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = null;
		Blob blob = null;
		try {
			MemberBean memberBean = memberService.queryMember(account);
			MerchantBean merchantBean = merchantService.queryMerchant(account);
			if (memberBean != null) {
				blob = memberBean.getCusPhoto();
				if (blob != null) { 
					is = blob.getBinaryStream();
				}
				fileName = memberBean.getCusFileName();
			} else if (merchantBean != null) {
				blob = merchantBean.getBusPhoto();
				if (blob != null) { 
					is = blob.getBinaryStream();
				}
				fileName = merchantBean.getBusFileName();
			}
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
			if (is == null) {
				fileName = "no_image.png" ; 
				is = servletContext.getResourceAsStream(
						"/resources/images/" + fileName);
			}
			// 由圖片檔的檔名來得到檔案的MIME型態
			mimeType = servletContext.getMimeType(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			
			while ((len = is.read(bytes)) != -1) {
				baos.write(bytes, 0, len);
			}
			media = baos.toByteArray();
			mediaType = MediaType.valueOf(mimeType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			headers.setContentType(mediaType);
			responseEntity =  new ResponseEntity<>(media, headers, HttpStatus.OK);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("_00_init.util.RetrieveBookImageServlet#doGet()發生Exception: " + ex.getMessage());
		} finally{
			try {
				if (is != null) is.close();
			} catch(IOException e) {
				;
			}
			try {
				if (os != null) os.close();
			} catch(IOException e) {
				;
			}
		}
		return responseEntity;
	}
	
}
