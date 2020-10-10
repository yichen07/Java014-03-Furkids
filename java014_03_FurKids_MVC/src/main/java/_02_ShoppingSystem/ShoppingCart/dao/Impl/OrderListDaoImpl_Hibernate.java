package _02_ShoppingSystem.ShoppingCart.dao.Impl;

import java.sql.Connection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import _02_ShoppingSystem.ShoppingCart.dao.OrderListDao;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.ude.ProductStockException;

@Repository
public class OrderListDaoImpl_Hibernate implements OrderListDao {
	@Autowired
	SessionFactory factory;

	public OrderListDaoImpl_Hibernate() {		
	}
	
	@Override
	public double findItemAmount(OrderListBean oib) {
		double subtotal = oib.getOrdQuantity() * oib.getOrdUnitPrice();
		return subtotal;	
		}

	@Override
	public int updateProductStock(OrderListBean oib) {
		int n = 0;
		Integer stock = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT ComStock FROM CommodityBean WHERE ComID = :comID";
		String hql1 = "UPDATE CommodityBean SET ComStock = ComStock - :orderAmount WHERE ComId = :comID";
		stock = (Integer) session.createQuery(hql0)
                                 .setParameter("comID", oib.getComID())
                                 .getSingleResult();
		if (stock == null) {
            stock = 0;
        }
        int stockLeft = stock = oib.getOrdQuantity();
        if (stockLeft < 0) {
            throw new ProductStockException(
                      "庫存數量不足 商品: " + oib.getComName() + ", 庫存量: "
                       + stock + ", 訂購量: " + oib.getOrdQuantity());
        }
		n = session.createQuery(hql1)
                   .setParameter("comID", oib.getComID())
                   .setParameter("orderAmount", oib.getOrdQuantity())
                   .executeUpdate();
		return n;	
    }

	@Override
	public void setConnection(Connection conn) {
		throw new RuntimeException("本類別未實作此方法");
	}

}
