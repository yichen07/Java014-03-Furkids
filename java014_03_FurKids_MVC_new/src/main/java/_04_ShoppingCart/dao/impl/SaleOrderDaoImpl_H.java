package _04_ShoppingCart.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_ShoppingCart.dao.SaleOrderDao;
import _04_ShoppingCart.model.SaleOrderBean;
// 本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
//   3.查詢orders表格內的所有訂單
@Repository
public class SaleOrderDaoImpl_H implements SaleOrderDao {
	
	private int m_No = 0;
	@Autowired
	private SessionFactory factory;
	int orderNo = 0;

	public SaleOrderDaoImpl_H() {
	}

	@Override
	public void insertSaleOrder(SaleOrderBean sob) {
		Session session = factory.getCurrentSession();
        session.save(sob);
	}

	public SaleOrderBean getSaleOrder(int s_OrderNo) {
		SaleOrderBean sob = null;
        Session session = factory.getCurrentSession();
        sob = session.get(SaleOrderBean.class, s_OrderNo);
        return sob;
	}

	public int getM_No() {
		return m_No;
	}

	public void setM_No(int m_No) {
		this.m_No = m_No;
	}

	public void setConnection(Connection con) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrderBean> getAllSaleOrders() {
		List<SaleOrderBean> list = null;
		String hql = "FROM SaleOrdersBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrderBean> getMemberSaleOrders(int m_No) {
		List<SaleOrderBean> list = null;
        Session session = factory.getCurrentSession();
        String hql = "FROM SaleOrderBean ob WHERE ob.s_M_No = :m_No";
        list = session.createQuery(hql)
        			  .setParameter("m_No", m_No)
        			  .getResultList();
        return list;
	}
	
}