package _03_FriendlyService.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import _01_Member.Registration.model.MerchantBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;

@Controller
@RequestMapping("/_03_FriendlySystem")
@SessionAttributes({"nowPage","AllConvenience","NotConvenience","TotalPages" })
public class ConvenienceViewController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ConvenienceService service;
	
	@GetMapping("/Reservation/{item}")
	public String getPage(Model model,
			@PathVariable(value="item" ,required = false) String item) {
		String page = "1";
		if(model.getAttribute("nowPage") != null) {
			 page = (String) model.getAttribute("nowPage");
		} 
		return "redirect:/_03_FriendlySystem/Reservation/" + item + "/" + page;
	}
	
	
	@GetMapping("/Reservation/{item}/{page}")
	public String list(Model model,
			@PathVariable(value="page" ,required = false) String page,
			@PathVariable(value="item" ,required = false) String item) {
		model.addAttribute("nowPage", page);
		Integer intPageNo =  Integer.parseInt(page);
		List<ConvenienceBean_H> cb = service.getPageViewConvenience(item, intPageNo);
		model.addAttribute("AllConvenience",cb);
		return "/_03_FriendlySystem/tourList_hotel";
	}
	
	//撈該分店圖片
		@GetMapping("/getSmallPicture/{id}")
		public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, 
				@PathVariable(value="id" ,required = false) String id) {
			
			InputStream is = null;
			ResponseEntity<byte[]> re = null;
			HttpHeaders headers = new HttpHeaders();
			String fileName = "";
			MerchantBean mb = service.getBus(id);
			try {
				if (mb != null) {
					Blob busPhoto = mb.getBusPhoto();
					if (busPhoto != null) {
						is = busPhoto.getBinaryStream();
						fileName = mb.getBusFileName();
					}
				}
				if (is == null) {
					is = servletContext.getResourceAsStream("/resources/images/NoImage.jpg");
					fileName = "/resources/images/NoImage.jpg";
				}
				byte[] b = new byte[81920];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len = 0;
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
				String mimeType = servletContext.getMimeType(fileName);
				MediaType mediaType = MediaType.valueOf(mimeType);
				headers.setContentType(mediaType);
//				re = ResponseEntity.ok(baos.toByteArray());
				re = new ResponseEntity<>(baos.toByteArray(), headers , HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return re;
		}
	
}
