package _04_ShoppingCart.dao;

import java.sql.Connection;
import java.util.List;

import _04_ShoppingCart.model.SaleOrderBean;

public interface SaleOrderDao {

	void insertSaleOrder(SaleOrderBean sob);

	void setConnection(Connection con);

	SaleOrderBean getSaleOrder(int s_OrderNo);

	List<SaleOrderBean> getAllSaleOrders();

	List<SaleOrderBean> getMemberSaleOrders(int m_No);

}