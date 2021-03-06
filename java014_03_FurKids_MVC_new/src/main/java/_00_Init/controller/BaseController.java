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

import _00_Init.service.ProductInfoService;
import _00_Init.util.GlobalService;
import _01_Member.Login.model.LoginBean;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.Registration.service.MerchantService;
import _03_listProducts.model.ProductBean;
import _03_listProducts.service.ProductService;

@Controller
@SessionAttributes("loginBean")
public class BaseController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MerchantService merchantService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductInfoService productInfoService;
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
	
	// 本方法與前一個方法極為類似，由於兩方法之參數 id 的型態不同，所以無法合而為一
//		@GetMapping("/_00_Init/getBookImage")
//		public ResponseEntity<byte[]> getBookImage(
//				@RequestParam("id") Integer id 
//		) {
//			InputStream is = null;
//			OutputStream os = null;
//			String fileName = null;
//			String mimeType = null;
//			byte[] media = null;
//			ResponseEntity<byte[]> responseEntity = null;
//			HttpHeaders headers = new HttpHeaders();
//			MediaType mediaType = null;
//			Blob blob = null;
//			try {
//				CommodityBean bean = commodityService.getCommodity(id);
//				if (bean != null) {
//					blob = bean.getComImage();
//					if (blob != null) {
//						is = blob.getBinaryStream();
//					}
//					fileName = bean.getFileName();
//				}
//				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
//				if (is == null) {
//					fileName = "no_image.png" ; 
//					is = servletContext.getResourceAsStream(
//							"/resources/images/" + fileName);
//				}
//				// 由圖片檔的檔名來得到檔案的MIME型態
//				mimeType = servletContext.getMimeType(fileName);
////				if (mimeType == null) {
////					if (fileName.endsWith("jfif")) {
////						mimeType = "image/jfif";		
////					}
////				}
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				// 由InputStream讀取位元組，然後由OutputStream寫出
//				int len = 0;
//				byte[] bytes = new byte[8192];
//				
//				while ((len = is.read(bytes)) != -1) {
//					baos.write(bytes, 0, len);
//				}
//				media = baos.toByteArray();
//				mediaType = MediaType.valueOf(mimeType);
//				headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//				headers.setContentType(mediaType);
//				responseEntity =  new ResponseEntity<>(media, headers, HttpStatus.OK);
//				
//			} catch(Exception ex) {
//				ex.printStackTrace();
//				throw new RuntimeException("_00_init.util.RetrieveBookImageServlet#doGet()發生Exception: " + ex.getMessage());
//			} finally{
//				try {
//					if (is != null) is.close();
//				} catch(IOException e) {
//					;
//				}
//				try {
//					if (os != null) os.close();
//				} catch(IOException e) {
//					;
//				}
//			}
//			return responseEntity;
//		}
		
		@GetMapping("ThanksForOrdering")
		protected String thanksForOrdering(Model model) {
			System.out.println("ThanksForOrdering......");
			return "_04_ShoppingCart/ThanksForOrdering";
		}
		
		
		// 取得寵物大頭照。
		@GetMapping("/_00_init/getPetHeadshot")
		public ResponseEntity<byte[]>  getPetHeadshot(@RequestParam("petID") Integer petID) { 	// 送回圖片ResponseEntity<byte[]>；@RequestParam("id") ==> //..../_00_init/getMemberImage/?id=5
//		@GetMapping("/_00_init/getMemberImage/{id}")
//		public ResponseEntity<byte[]>  getMemberImage(@PathVariable("id") String id) { 	// 送回圖片ResponseEntity<byte[]>；@PathVariable("id") ==> //..../_00_init/getMemberImage/5
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
				PetBean petBean= memberService.queryPet(petID);
				
				if (petBean != null) {
					blob = petBean.getPetPhoto();
					if (blob != null) { 
						is = blob.getBinaryStream();
					}
					fileName = petBean.getPetFileName();
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
				throw new RuntimeException("_00_init.util.RetrievePetImageServlet#doGet()發生Exception: " + ex.getMessage());
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
		
// -------------------------------------------------------------------------------------------------------------------------------------------------
		
		// 本方法與前一個方法極為類似，由於兩方法之參數 id 的型態不同，所以無法合而為一
		@GetMapping("/_00_init/getProductImage")
		public ResponseEntity<byte[]> getProductImage(
				@RequestParam("id") Integer id 
		) {
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
				ProductBean bean = productService.getProductById(id);
						;
				if (bean != null) {
					blob = bean.getP_Cover();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					fileName = bean.getP_FileName();
				}
				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
				if (is == null) {
					fileName = "NoImage.png" ; 
					is = servletContext.getResourceAsStream(
							"/data/Image/" + fileName);
				}
				// 由圖片檔的檔名來得到檔案的MIME型態
				mimeType = servletContext.getMimeType(fileName);
//				if (mimeType == null) {
//					if (fileName.endsWith("jfif")) {
//						mimeType = "image/jfif";		
//					}
//				}
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
				throw new RuntimeException("_00_init.util.RetrieveProductImageServlet#doGet()發生Exception: " + ex.getMessage());
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
		
		
		
		
		// 控制預覽圖片p_FileName1
		@GetMapping("/_00_init/p_Pic1")
		public ResponseEntity<byte[]> getP_Pic1(@RequestParam("id") Integer id) {
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
				System.out.println("id" + id);
				ProductBean bean = productInfoService.getSupPageProductsById(id);
				System.out.println(bean);
				if (bean != null) {
					blob = bean.getP_Pic1();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					fileName = bean.getP_FileName1();
				}

				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
				if (is == null) {
					fileName = "no_image.png";
					is = servletContext.getResourceAsStream("/resources/images/" + fileName);
				}
				// 由圖片檔的檔名來得到檔案的MIME型態
				mimeType = servletContext.getMimeType(fileName);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 由InputStream讀取位元組，然後由OutputStream寫出
				int len = 0;
				byte[] bytes = new byte[81920];
				System.out.println("yyyyyyyyyyyyyy");
				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				media = baos.toByteArray();
				mediaType = MediaType.valueOf(mimeType);
				headers.setCacheControl(CacheControl.noCache().getHeaderValue());
				headers.setContentType(mediaType);
				responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(
						"_00_init.util.RetrieveProductImageServlet#doGet()發生Exception: " + ex.getMessage());
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
					;
				}
				try {
					if (os != null)
						os.close();
				} catch (IOException e) {
					;
				}
			}
			System.out.println("圖1成功!");
			return responseEntity;
		}

		// 控制預覽圖片p_FileName2
		@GetMapping("/_00_init/productImage2")
		public ResponseEntity<byte[]> getP_FileName2(@RequestParam("id") Integer id) {
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
				ProductBean bean = productInfoService.getSupPageProductsById(id);
				;
				if (bean != null) {

					blob = bean.getP_Pic2();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					fileName = bean.getP_FileName2();
				}

				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
				if (is == null) {
					fileName = "no_image.png";
					is = servletContext.getResourceAsStream("/resources/images/" + fileName);
				}
				// 由圖片檔的檔名來得到檔案的MIME型態
				mimeType = servletContext.getMimeType(fileName);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 由InputStream讀取位元組，然後由OutputStream寫出
				int len = 0;
				byte[] bytes = new byte[81920];

				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				media = baos.toByteArray();
				mediaType = MediaType.valueOf(mimeType);
				headers.setCacheControl(CacheControl.noCache().getHeaderValue());
				headers.setContentType(mediaType);
				responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(
						"_00_init.util.RetrieveProductImageServlet#doGet()發生Exception: " + ex.getMessage());
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
					;
				}
				try {
					if (os != null)
						os.close();
				} catch (IOException e) {
					;
				}
			}
			return responseEntity;
		}

		// 控制預覽圖片
		@GetMapping("/_00_init/productImage3")
		public ResponseEntity<byte[]> getP_FileName3(@RequestParam("id") Integer id) {
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
				ProductBean bean = productInfoService.getSupPageProductsById(id);
				;
				if (bean != null) {

					blob = bean.getP_Pic3();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					fileName = bean.getP_FileName3();
				}

				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
				if (is == null) {
					fileName = "no_image.png";
					is = servletContext.getResourceAsStream("/resources/images/" + fileName);
				}
				// 由圖片檔的檔名來得到檔案的MIME型態
				mimeType = servletContext.getMimeType(fileName);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 由InputStream讀取位元組，然後由OutputStream寫出
				int len = 0;
				byte[] bytes = new byte[81920];

				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				media = baos.toByteArray();
				mediaType = MediaType.valueOf(mimeType);
				headers.setCacheControl(CacheControl.noCache().getHeaderValue());
				headers.setContentType(mediaType);
				responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(
						"_00_init.util.RetrieveProductImageServlet#doGet()發生Exception: " + ex.getMessage());
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
					;
				}
				try {
					if (os != null)
						os.close();
				} catch (IOException e) {
					;
				}
			}
			
			return responseEntity;
		}
		
		
}
