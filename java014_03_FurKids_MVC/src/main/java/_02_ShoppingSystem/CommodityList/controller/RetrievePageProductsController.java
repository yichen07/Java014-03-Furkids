package _02_ShoppingSystem.CommodityList.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantBean;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;
import _02_ShoppingSystem.CommodityList.service.CommodityService;
import _02_ShoppingSystem.CommodityList.service.Impl.CommodityServiceImpl;

@Controller
@RequestMapping("_02_ShoppingSystem")
@SessionAttributes({ "LoginOK", "pageNo", "products_DPP", "loginBean" , "sort"})
public class RetrievePageProductsController {
	static int x = 0;
	@Autowired
	ServletContext context;
	@Autowired
	CommodityService service;

	@GetMapping("/DisplayPageProducts")
	public String getPageProduct(Model model, 
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pageNo			
			) {	


		String memberId = null;

		Object obj = model.getAttribute("LoginOK");
		//MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		//MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
        
		if(obj == null) {
		   memberId = "guest";
		}else if(obj instanceof MemberBean) {
			MemberBean mb = (MemberBean) model.getAttribute("LoginOK");
			memberId = mb.getCusAccount();
		}else {
			MerchantBean mcb = (MerchantBean) model.getAttribute("LoginOK");
			memberId = mcb.getBusAccount();
		}
		
//		if(obj != null) {
//		   memberId = memberBean.getCusAccount();;
//		}else if(merchantBean != null) {
//			memberId = merchantBean.getBusAccount();
//		}else {
//			memberId = "guest";
//		}

		if (pageNo == null) {  
			pageNo = 1;
			// 讀取瀏覽器送來的所有 Cookies
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				// 逐筆檢視Cookie內的資料
				for (Cookie c : cookies) {
					if (c.getName().equals(memberId + "pageNo")) {
						try {
							pageNo = Integer.parseInt(c.getValue().trim());
						} catch (NumberFormatException e) {
							;
						}
						break;
					}
				}
			}
		}

		model.addAttribute("baBean", service);
		Map<Integer, CommodityBean> CommodityMap;

		    CommodityMap = service.getPageCommodity(pageNo);
		
		model.addAttribute("pageNo", String.valueOf(pageNo));
		model.addAttribute("totalPages", service.getTotalPages());

		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		model.addAttribute("products_DPP", CommodityMap);

		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
//		Cookie pnCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
		Cookie pnCookie = new Cookie("userId",memberId);

	    // 設定Cookie的存活期為30天
		pnCookie.setMaxAge(30 * 24 * 60 * 60);
	    // 設定Cookie的路徑為 Context Path		
		pnCookie.setPath(request.getContextPath());
		// 將Cookie加入回應物件內
		response.addCookie(pnCookie);
		// -----------------------
		// 交由listBooks.jsp來顯示某頁的書籍資料，同時準備『第一頁』、
		// 『前一頁』、『下一頁』、『最末頁』等資料
//		RequestDispatcher rd = request.getRequestDispatcher("shoppinglist.jsp");
//		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//		rd.forward(request, response);
		return "_02_ShoppingSystem/shoppinglist";

	}
	
	
	
	@GetMapping("/DisplayPageProductsSort")
	public String getPageProductSort(Model model, 
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "sort", required = false) String sort
			) {	


		String memberId = null;

		Object obj = model.getAttribute("LoginOK");
		//MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		//MerchantBean merchantBean = (MerchantBean) model.getAttribute("LoginOK");
        
		if(obj == null) {
		   memberId = "guest";
		}else if(obj instanceof MemberBean) {
			MemberBean mb = (MemberBean) model.getAttribute("LoginOK");
			memberId = mb.getCusAccount();
		}else {
			MerchantBean mcb = (MerchantBean) model.getAttribute("LoginOK");
			memberId = mcb.getBusAccount();
		}
		
//		if(obj != null) {
//		   memberId = memberBean.getCusAccount();;
//		}else if(merchantBean != null) {
//			memberId = merchantBean.getBusAccount();
//		}else {
//			memberId = "guest";
//		}


		model.addAttribute("baBean", service);

		Map<Integer, CommodityBean> CommodityMap = service.getPageCommoditySort(sort);
//		model.addAttribute("pageNo", String.valueOf(pageNo));
//		model.addAttribute("totalPages", service.getTotalPages());

		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		model.addAttribute("products_DPP", CommodityMap);

		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
//		Cookie pnCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
		Cookie pnCookie = new Cookie("userId",memberId);

	    // 設定Cookie的存活期為30天
		pnCookie.setMaxAge(30 * 24 * 60 * 60);
	    // 設定Cookie的路徑為 Context Path		
		pnCookie.setPath(request.getContextPath());
		// 將Cookie加入回應物件內
		response.addCookie(pnCookie);
		// -----------------------
		// 交由listBooks.jsp來顯示某頁的書籍資料，同時準備『第一頁』、
		// 『前一頁』、『下一頁』、『最末頁』等資料
//		RequestDispatcher rd = request.getRequestDispatcher("shoppinglist.jsp");
//		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//		rd.forward(request, response);
		return "_02_ShoppingSystem/shoppinglist_sort";

	}
}