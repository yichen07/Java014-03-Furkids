package _02_ShoppingSystem.ShoppingCart.dao.Impl;

import java.sql.Connection;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00_Init.util.DBService;
import _02_ShoppingSystem.ShoppingCart.dao.OrderDao;
import _02_ShoppingSystem.ShoppingCart.model.OrderBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;

@Repository
public class OrderDaoImpl_Hibernate implements OrderDao {

	private String memberId = null;
	@Autowired
	private SessionFactory factory;
	int ordID = 0;
	
	public OrderDaoImpl_Hibernate() {
		
	}
	
	@Override
	public void insertOrder(OrderBean ob) {	 
        Session session = factory.getCurrentSession();
        session.save(ob);
      
	}	 

	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
        Session session = factory.getCurrentSession();
        ob = session.get(OrderBean.class, ordID);
        return ob;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	

	public void setConnection(Connection con) {

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean";
        Session session = factory.getCurrentSession();
     
        list = session.createQuery(hql).getResultList();
		return list;	
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getMemberOrders(String memberId) {
		Session session = factory.getCurrentSession();
		List<OrderBean> list = null;
		String hql = "FROM OrderBean ob where ob.cusAccount = :mid Order by orderDateTime desc ";
		list = session.createQuery(hql)
                      .setParameter("mid", memberId)
                      .getResultList();
		return list;	
		}

}
