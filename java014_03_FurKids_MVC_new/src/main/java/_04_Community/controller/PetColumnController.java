package _04_Community.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import _00_Init.util.GlobalService;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _04_Community.model.PetColumnBean;
import _04_Community.service.PetColumnService;

@Controller
@SessionAttributes({ "loginBean", "LoginOK" })
public class PetColumnController {

	@Autowired
	PetColumnService pcs;
	@Autowired
	ServletContext servletContext;

	@GetMapping("/BlogIndex")
	public String blogIndex(Model model) {
		try {
			List<PetColumnBean> pcbList = pcs.selectBlogIndexAll();
			model.addAttribute("pcbList", pcbList);
			Map<String, Integer> PCNumberOfMessages = new HashMap<>();
			for (PetColumnBean pcb : pcbList) {
				int pcfid = pcs.getpcfidCOUNT(pcb.getPCID());
				PCNumberOfMessages.put(pcb.getPCID(), pcfid);
			}
			model.addAttribute("PCNumberOfMessages", PCNumberOfMessages);
			List<PetColumnBean> popularPcbList = pcs.selectPopularBlogIndex();
			for (PetColumnBean ppcb : popularPcbList) {
				ppcb.setPCDateTime(ppcb.getPCDateTime().substring(0, 10).replaceAll("-", "/"));
			}
			model.addAttribute("popularPcbList", popularPcbList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "_04_Community/blogIndex";
	}

	@GetMapping("/GetPCImage")
	public ResponseEntity<byte[]> getPCImage(HttpServletRequest request) {
		InputStream is = null;
		ResponseEntity<byte[]> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			request.setCharacterEncoding("UTF-8");
			PetColumnBean pcb = pcs.getPetColumn(request.getParameter("PCID"));
			Blob PCImage = pcb.getPCImage();
			if (PCImage != null)
				is = PCImage.getBinaryStream();
			// 由圖片檔的檔名來得到檔案的MIME型態
			String mimeType = servletContext.getMimeType(pcb.getPCImageName());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				baos.write(bytes, 0, len);
			}
			is.close();
			byte[] media = baos.toByteArray();
			MediaType mediaType = MediaType.valueOf(mimeType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			headers.setContentType(mediaType);
			responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@PostMapping("/SaveBlogIndex")
	public String saveBlogIndex(@RequestParam("blogImg") MultipartFile img, Model model, HttpServletRequest request) {
		InputStream is = null;
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			PetColumnBean pcb = new PetColumnBean();
			if (session.getAttribute("LoginOK") instanceof MerchantBean) {
				MerchantBean merchantBean = (MerchantBean) session.getAttribute("LoginOK");
				pcb.setPCFounder(merchantBean.getBusName());
				pcb.setPCAccount(merchantBean.getBusAccount());
			} else if (session.getAttribute("LoginOK") instanceof MemberBean) {
				MemberBean memberBean = (MemberBean) session.getAttribute("LoginOK");
				pcb.setPCFounder(memberBean.getCusName());
				pcb.setPCAccount(memberBean.getCusAccount());
			}
			// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
			pcb.setPCTitle(request.getParameter("blogTitle"));
			pcb.setPCContent(request.getParameter("blogContent"));
			pcb.setPCImageName(img.getOriginalFilename());
			is = img.getInputStream();
			if (is != null)
				pcb.setPCImage(GlobalService.fileToBlob(is, img.getBytes().length));
			is.close();
			pcs.saveBlogIndex(pcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blogIndex(model);
	}

	@GetMapping("/BlogArtical")
	public String blogArtical(Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			String pcid = request.getParameter("PCID");
			PetColumnBean pcb = pcs.getPetColumn(pcid);
			pcb.setPCDateTime(pcb.getPCDateTime().replaceAll("-", "/"));
			model.addAttribute("pcb", pcb);
			pcs.updatePCViews(pcb.getPCID(), pcb.getPCViews());
			List<PetColumnBean> pcbList = pcs.selectBlogArticalAll(pcid);
			for (PetColumnBean bpcb : pcbList) {
				bpcb.setPCDateTime(bpcb.getPCDateTime().replaceAll("-", "/"));
			}
			model.addAttribute("pcbList", pcbList);
			String pctitle = pcb.getPCTitle();
			List<PetColumnBean> relatedBlogArtical = new ArrayList<>();
			List<String> sList = new ArrayList<>();
			int n = 10;
			while (relatedBlogArtical.size() < 3) {
				if (pctitle.length() >= n) {
					sList.clear();
					for (int i = 0; i + n <= pctitle.length(); i++) {
						sList.add(pctitle.substring(i, i + n));
					}
					relatedBlogArtical = pcs.selectRelatedBlogArtical(sList, pcid);
					n--;
				} else {
					n--;
				}
				if (n == 0) {
					if (relatedBlogArtical.size() > 0) {
						for (PetColumnBean ppcb : pcs.selectPopularBlogIndex()) {
							relatedBlogArtical.add(ppcb);
							if (relatedBlogArtical.size() == 3)
								break;
						}
					} else {
						relatedBlogArtical = pcs.selectPopularBlogIndex();
					}
					for (PetColumnBean rpcb : relatedBlogArtical) {
						rpcb.setPCDateTime(rpcb.getPCDateTime().substring(0, 10).replaceAll("-", "/"));
					}
				}
			}
			model.addAttribute("relatedBlogArtical", relatedBlogArtical);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "_04_Community/blogArtical";
	}

	@PostMapping("/SaveBlogArtical")
	public String saveBlogArtical(Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			PetColumnBean pcb = new PetColumnBean();
			if (model.getAttribute("LoginOK") instanceof MerchantBean) {
				MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
				pcb.setPCFounder(merchantBean.getBusName());
				pcb.setPCAccount(merchantBean.getBusAccount());
			} else if (model.getAttribute("LoginOK") instanceof MemberBean) {
				MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
				pcb.setPCFounder(memberBean.getCusName());
				pcb.setPCAccount(memberBean.getCusAccount());
			}
			pcb.setPCContent(request.getParameter("blogContent"));
			pcb.setPCFID(request.getParameter("PCID"));
			pcs.saveBlogArtical(pcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blogArtical(model, request);
	}

	@PostMapping("/UpdatePetColumn")
	public String updatePetColumn(@RequestParam("blogImg") MultipartFile img, Model model, HttpServletRequest request) {
		InputStream is = null;
		try {
			request.setCharacterEncoding("UTF-8");
			PetColumnBean pcb = pcs.getPetColumn(request.getParameter("PCID"));
			pcb.setPCTitle(request.getParameter("blogTitle"));
			pcb.setPCContent(request.getParameter("blogContent"));
			if (img.getSize() > 0) {
				pcb.setPCImageName(img.getOriginalFilename());
				is = img.getInputStream();
				if (is != null)
					pcb.setPCImage(GlobalService.fileToBlob(is, img.getBytes().length));
				is.close();
			}
			pcs.updatePetColumn(pcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blogIndex(model);
	}
	
	@GetMapping("/deleteBlogArtical")
	public String deleteBlogArtical(Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			pcs.deletePetColumn(request.getParameter("PCID"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blogIndex(model);
	}
}
