package _03_listProducts.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import _01_Member.Registration.model.MemberBean;
import _03_listProducts.model.ProductBean;
import _03_listProducts.service.ProductService;
import _04_ShoppingCart.model.SaleOrderItemsBean;
import _04_ShoppingCart.model.ShoppingCart;
// 當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
@Controller
@RequestMapping("_03_listProducts")
@SessionAttributes({ "LoginOK", "pageNo", "products_DPP", "ShoppingCart" ,"p_Category", "loginBean"})
public class BuyProductController {

	@Autowired
	ServletContext context;
	@Autowired
	ProductService service;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/BuyProduct.do")
	protected String buyBook(Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAtt) throws ServletException, IOException {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
	
		HttpSession session = request.getSession(false); 
		if (session == null) {
			redirectAtt.addFlashAttribute("errorNotLogin", "請先註冊或登入會員帳號");
			return "redirect:/";
		}
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
		// 如果找不到ShoppingCart物件
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			model.addAttribute("ShoppingCart", cart);   
		}
		String p_IdStr 	= request.getParameter("p_Id");
		int p_Id          = Integer.parseInt(p_IdStr.trim());
		String p_Category = request.getParameter("p_Category");
		String qtyStr 		= request.getParameter("qty");
		Integer qty = 0 ; 
		String pageNo 		= request.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0){
			pageNo = (String) model.getAttribute("pageNo") ;
			if (pageNo == null){
				pageNo = "1";
			} 
		} 
		int intPageNo=Integer.parseInt(pageNo);
//		Map<Integer, ProductBean> productMap2 = (Map<Integer, ProductBean>) session.getAttribute("products_DPP");
//		Map<Integer, ProductBean> productMap = service.getAllProducts(p_Category, intPageNo);
		Map<Integer, ProductBean> productMap = service.getAllProducts();
		ProductBean bean = productMap.get(p_Id);
		
		try{
			// 進行資料型態的轉換
			qty = Integer.parseInt(qtyStr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
		SaleOrderItemsBean soib = new  SaleOrderItemsBean(
				null, 
				p_Id, 
				bean.getP_Note(), 
				qty, 
				bean.getP_Price(), 
				bean.getP_Name(),
				bean.getP_Category());
		// 將OrderItem物件內加入ShoppingCart的物件內
		cart.addToCart(p_Id, soib);
		
		return  "redirect:/_03_listProducts/DisplayPageProducts2";
	}
}