package _02_ShoppingSystem.ShoppingCart.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;

public class ShoppingCart {
private Map<Integer, OrderListBean> cart = new LinkedHashMap< >();
	
	public ShoppingCart() {
	}
	
	public Map<Integer, OrderListBean>  getContent() { // ${ShoppingCart.content}
		return cart;
	}
	public void addToCart(int comID, OrderListBean  oib) {
		if (oib.getOrdQuantity() <= 0 ) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( cart.get(comID) == null ) {
		    cart.put(comID, oib);
		} else {
	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			OrderListBean oiBean = cart.get(comID);
			// 加購的數量：bean.getQuantity()
			// 原有的數量：oBean.getQuantity()			
			oiBean.setOrdQuantity(oib.getOrdQuantity() + oiBean.getOrdQuantity());
		}
	}

	public boolean modifyQty(int comID, int newQty) {
		if ( cart.get(comID) != null ) {
		   OrderListBean  bean = cart.get(comID);
		   bean.setOrdQuantity(newQty);
	       return true;
		} else {
		   return false;
		}
	}
	// 刪除某項商品
	public int deleteBook(int ComID) {
		if ( cart.get(ComID) != null ) {
	       cart.remove(ComID);  // Map介面的remove()方法
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return cart.size();
	}
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public double getSubtotal(){   //  subtotal
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			OrderListBean oib = cart.get(n);
			double price    = oib.getOrdUnitPrice();
			int qty      = oib.getOrdQuantity();
			subTotal +=  price * qty;
		}
		return subTotal;
	}
}
