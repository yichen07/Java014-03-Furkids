package _04_ShoppingCart.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
public class ShoppingCart {   
	
	private Map<Integer, SaleOrderItemsBean> cart = new LinkedHashMap< >();
	
	public ShoppingCart() {
	}
	
	public Map<Integer, SaleOrderItemsBean>  getContent() { // ${ShoppingCart.content}
		return cart;
	}
	public void addToCart(int p_Id, SaleOrderItemsBean  soib) {
		if (soib.getSoiQty() <= 0 ) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( cart.get(p_Id) == null ) {
		    cart.put(p_Id, soib);
		} else {
	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			SaleOrderItemsBean soiBean = cart.get(p_Id);
			// 加購的數量：bean.getQuantity()
			// 原有的數量：oBean.getQuantity()			
			soiBean.setSoiQty(soib.getSoiQty() + soiBean.getSoiQty());
		}
	}

	public boolean modifyQty(int p_Id, int newQty) {
		if ( cart.get(p_Id) != null ) {
		   SaleOrderItemsBean  bean = cart.get(p_Id);
		   bean.setSoiQty(newQty);
	       return true;
		} else {
		   return false;
		}
	}
	// 刪除某項商品
	public int deleteProduct(int p_Id) {
		if ( cart.get(p_Id) != null ) {
	       cart.remove(p_Id);  // Map介面的remove()方法
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return cart.size();
	}
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public double getSubtotal(){
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			SaleOrderItemsBean soib = cart.get(n);
			double price    = soib.getSoiPrice();
			int qty      = soib.getSoiQty();
			subTotal +=  price * qty;
		}
		return subTotal;
	}
}
