package _02_ShoppingSystem.ShoppingCart.dao;

import java.sql.Connection;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;

public interface OrderListDao {

	// 由 OrderItemBean取得商品價格(eBook#Price)。
	
		double findItemAmount(OrderListBean oib);

		int updateProductStock(OrderListBean ob);
		
		void setConnection(Connection conn);
}
