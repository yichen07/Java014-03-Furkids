package _01_Member.Registration.dao.impl;

import java.sql.Connection;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.util.HibernateUtils;

public class MerchantChildDaoImpl_Hibernate implements MerchantChildDao {
	
	SessionFactory factory;
	
	public MerchantChildDaoImpl_Hibernate() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	// 儲存MerchantChildBean物件，將商家分店註冊資料mb新增到資料庫MerchantChildRegistration表格中。
	@Override
	public int saveMerchantChild(MerchantChildBean mcb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mcb);
		n++;
		return n;
	}

	// 由參數BusAccount(商家帳號)到MerchantChildRegistration表格中取得某個商家的所有資料，
	// 傳回值為一個MerchantChildBean的物件；如果找不到對應的商家資料，傳回值為null。
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		MerchantChildBean mcb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantChildBean m WHERE m.busAccount = :account";
		try {
			mcb = (MerchantChildBean) session.createQuery(hql).setParameter("account", account).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			mcb = null;
		}
		return mcb;
	}

	// 判斷參數CusAccount(會員帳號)是否已經被現有會員或商家使用，
		// 如果是，傳回true，表示此CusAccount(會員帳號)不能使用，
		// 否則傳回false，表示此CusAccount(會員帳號)可使用。
		@Override
		public boolean merchantChildExists(String account, String address) {
			boolean exist = false;
			Session session = factory.getCurrentSession();
			String hql = "FROM MerchantChildBean m WHERE m.busAccount = :account AND m.busChildAddress = :address ";
			try {
				MerchantChildBean mcb = (MerchantChildBean) session.createQuery(hql).setParameter("account", account).setParameter("address", address).getSingleResult();
//				Object mcb = session.createQuery(hql).setParameter("account", account).setParameter("address", address).getSingleResult();
				if (mcb != null) {
					exist = true;
				}
			} catch (NoResultException e) {
//				e.printStackTrace();
				exist = false;
			} catch (NoUniqueBeanDefinitionException e) {
//				e.printStackTrace();
				exist = true;
			}
			return exist;
		}
	
	
	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("MerchantChildDaoImpl_Hibernate類別不支援setConnection()方法");
	}

}
