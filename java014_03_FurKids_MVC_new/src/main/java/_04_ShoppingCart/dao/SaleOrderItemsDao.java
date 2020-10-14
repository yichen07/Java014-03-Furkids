package _04_ShoppingCart.dao;

import java.sql.Connection;

import _04_ShoppingCart.model.SaleOrderItemsBean;

public interface SaleOrderItemsDao {
	
	// 由 OrderItemBean取得商品價格(eBook#Price)。
		
	double findItemAmount(SaleOrderItemsBean soib);

	int updateProductStock(SaleOrderItemsBean soib);
	
	void setConnection(Connection conn);
}
