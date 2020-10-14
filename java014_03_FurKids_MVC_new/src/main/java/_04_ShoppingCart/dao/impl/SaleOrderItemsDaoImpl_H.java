package _04_ShoppingCart.dao.impl;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_ShoppingCart.dao.SaleOrderItemsDao;
import _04_ShoppingCart.model.SaleOrderItemsBean;
import _04_ShoppingCart.ude.ProductStockException;
/*
 * 一張合格的訂單必須經過下列檢查 
 * 
 * 	1.	檢查訂購之商品的數量是否足夠。
 *      此功能寫在本類別的updateProductStock()方法內，參考該方法的說明。
 */
@Repository
public class SaleOrderItemsDaoImpl_H implements SaleOrderItemsDao {
	@Autowired
	SessionFactory factory;

	public SaleOrderItemsDaoImpl_H() {
	}
	/*
	 * 計算客戶欲購買之某項商品(以OrderItemBean物件oib來表示)的小計金額(subtotal)， 計算公式為: 商品的數量 * 商品的單價 *
	 * 商品的折扣
	 */
	@Override
	public double findItemAmount(SaleOrderItemsBean soib) {
		double subtotal = soib.getSoiQty() * soib.getSoiPrice();
		return subtotal;
	}
	@Override
	public int updateProductStock(SaleOrderItemsBean soib) {
		int n = 0;
		Integer stock = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT p_Stock FROM ProductBean WHERE p_Id = :Soi_P_Id";
		String hql1 = "UPDATE ProductBean SET p_Stock = p_Stock - :orderAmount WHERE p_Id = :Soi_P_Id";
		stock = (Integer) session.createQuery(hql0)
								 .setParameter("Soi_P_Id", soib.getSoi_P_Id())
								 .getSingleResult();
		if (stock == null) {
			stock = 0;
		}
		int stockLeft = stock - soib.getSoiQty();
		if (stockLeft < 0) {
			throw new ProductStockException(
					"庫存數量不足: BookId: " + soib.getSoi_P_Id() + ", 在庫量: " 
				    + stock + ", 訂購量: " + soib.getSoiQty());
		}
		n = session.createQuery(hql1)
				   .setParameter("Soi_P_Id", soib.getSoi_P_Id())
				   .setParameter("orderAmount", soib.getSoiQty())
				   .executeUpdate();
		return n;
	}
	@Override
	public void setConnection(Connection conn) {
		throw new RuntimeException("本類別未實作此方法");
	}

}
