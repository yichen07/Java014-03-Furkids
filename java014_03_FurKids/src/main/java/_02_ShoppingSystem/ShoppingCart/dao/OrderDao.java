package _02_ShoppingSystem.ShoppingCart.dao;

import java.sql.Connection;
import java.util.List;

import _02_ShoppingSystem.ShoppingCart.model.OrderBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);
	
	void setConnection(Connection con);
	
	OrderBean getOrder(int orderNo);
	
	List<OrderBean> getAllOrders();
	
	List<OrderBean> getMemberOrders(String memberId);
	
}
