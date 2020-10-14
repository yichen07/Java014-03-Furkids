package _04_ShoppingCart.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_ShoppingCart.dao.OrderDao;
import _04_ShoppingCart.model.OrderBean;
// 本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
//   3.查詢orders表格內的所有訂單
@Repository
public class OrderDaoImpl_Hibernate implements OrderDao {
	
	private String memberId = null;
	@Autowired
	private SessionFactory factory;
	int orderNo = 0;

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
        ob = session.get(OrderBean.class, orderNo);
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
		List<OrderBean> list = null;
        Session session = factory.getCurrentSession();
        String hql = "FROM OrderBean ob WHERE ob.memberId = :mid";
        list = session.createQuery(hql)
        			  .setParameter("mid", memberId)
        			  .getResultList();
        return list;
	}
	
}