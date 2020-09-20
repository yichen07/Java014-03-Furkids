package _03_FriendlyService.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;

@Controller
@RequestMapping("/_03_FriendlySystem")
public class ConvenienceController {
	@Autowired
	ServletContext servletContext;

	@Autowired
	ConvenienceService convenienceService;

	@RequestMapping("/convenience")
	public String list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		MerchantBean mb = null;
		String userId;
		if (session == null || (MerchantBean) session.getAttribute("LoginOK") == null) {
			return "redirect:/";
		} else {
			mb = (MerchantBean) session.getAttribute("LoginOK");
			userId = mb.getBusAccount();
		}

		// 抓傳進來的頁數，如果沒抓到，就從session抓，如果還是沒有，就給預設值第一頁

		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().length() == 0) {
			pageNoStr = (String) model.getAttribute("nowPage");
			if (pageNoStr == null) {
				pageNoStr = "1";
			}
		}
		// 把上面結果的頁數存進session
		model.addAttribute("nowPage", pageNoStr);
		int pageNo = Integer.parseInt(pageNoStr);

		// 總上架分店
		List<ConvenienceBean_H> allcb = convenienceService.getAllConvenience(userId);
		// 限制一次只抓8筆，從頁數去選擇要從哪筆開始抓
		List<ConvenienceBean_H> cb = convenienceService.getPageConvenience(userId, pageNo);
		// 尚未上架的商家分店
		List<MerchantChildBean> mcb = convenienceService.getNotConvenience(userId);

		// 總頁數
		int n = convenienceService.getTotalPages(userId);

		// 如果總上架的分店數是八的倍數且還有未上架的分店
		if (allcb.size() % 8 == 0 && mcb.size() > 0) {
			n = n + 1;
		}
		model.addAttribute("AllConvenience", cb);
		model.addAttribute("NotConvenience", mcb);
		model.addAttribute("TotalPages", n);
		return "/_03_FriendlySystem/convenience";
	}

	@GetMapping("/getPicture/{id}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer id) {
		InputStream is = null;
		ResponseEntity<byte[]> re = null;
		HttpHeaders headers = new HttpHeaders();
		String fileName = "";
		MerchantChildBean mcb = convenienceService.getBusChild(id);
		try {
			if (mcb != null) {
				Blob busChildPhoto = mcb.getBusChildPhoto();
				if (busChildPhoto != null) {
					is = busChildPhoto.getBinaryStream();
					fileName = mcb.getBusChildFileName();
				}
			}
			if (is == null) {
				is = servletContext.getResourceAsStream("/images/NoImage.png");
				fileName = "/images/NoImage.png";
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
//			re = ResponseEntity.ok(baos.toByteArray());
			re = new ResponseEntity<>(baos.toByteArray(), headers , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return re;
	}
}
