package _01_Member.Registration.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.model.MerchantBean;

@Repository
public class MerchantDaoImpl_Hibernate implements MerchantDao {
	
	SessionFactory factory;
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public MerchantDaoImpl_Hibernate() {
//		this.factory = HibernateUtils.getSessionFactory();
	}

	// 儲存MerchantBean物件，將商家註冊資料mb新增到資料庫MerchantRegistration表格中。
	@Override
	public int saveMerchant(MerchantBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}
	
	// 更新MerchantBean物件，更新資料庫MerchantRegistration表格中的商家資料。
	@Override
	public int updateMerchant(MerchantBean mb) {
		int n = 0;
		if (mb != null && mb.getBusAccount() != null) {
			Session session = factory.getCurrentSession();
			session.saveOrUpdate(mb);
			n++;			
		}
		return n;
	}
		
	// 刪除MerchantBean物件，刪除資料庫MerchantRegistration表格中的商家資料。
	@Override
	public int deleteMerchant(String account) {
		int n = 0;
		MerchantBean mb = queryMerchant(account);
		if (mb != null) {
			Session session = factory.getCurrentSession();
			session.delete(mb);
			n++;			
		}
		return n;
	}

	// 判斷參數BusAccount(商家帳號)是否已經被現有會員或商家使用，
	// 如果是，傳回true，表示此BusAccount(商家帳號)不能使用，
	// 否則傳回false，表示此BusAccount(商家帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantBean m WHERE m.busAccount = :account";
		try {
//			MerchantBean mb = (MerchantBean) session.createQuery(hql).setParameter("account", account).getSingleResult();
			Object mb = session.createQuery(hql).setParameter("account", account).getSingleResult();
			if (mb != null) {
				exist = true;
			}
		} catch (NoResultException e) {
//			e.printStackTrace();
			exist = false;
		} catch (NoUniqueBeanDefinitionException e) {
//			e.printStackTrace();
			exist = true;
		}
		return exist;
	}

	// 由參數BusAccount(商家帳號)到MerchantRegistration表格中取得某個商家的所有資料，
	// 傳回值為一個MerchantBean的物件；如果找不到對應的商家資料，傳回值為null。
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantBean m WHERE m.busAccount = :account";
		try {
			mb = (MerchantBean) session.createQuery(hql).setParameter("account", account).getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			mb = null;
		}
		return mb;
	}

	// 由MerchantRegistration表格中取得所有商家的資料，
	@SuppressWarnings("unchecked")
	// 傳回值為一個MerchantBean的List物件；如果找不到對應的商家資料，傳回值為null。
	@Override
	public List<MerchantBean> queryAllMerchants() {
		List<MerchantBean> mbs = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantBean";
		try {
			mbs = session.createQuery(hql).getResultList();
		} catch (Exception e) {
//			e.printStackTrace();
			mbs = null;
		}
		return mbs;
	}
	
	// 檢查使用者在登入時輸入的帳號密碼是否正確。
	// 如果正確傳回該帳號所對應的MerchantBean物件，否則傳回null。
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
//	public Object checkAccountPassword(String account, String password) {
		MerchantBean mb = null;
//		Object mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantBean m WHERE m.busAccount = :account and m.busPassword = :password";
		try {
			mb = (MerchantBean) session.createQuery(hql).setParameter("account", account).setParameter("password", password).getSingleResult();
//			mb = session.createQuery(hql).setParameter("account", account).setParameter("password", password).getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			mb = null;
		}
		return mb;
	}

	@Override
	public MerchantBean modifyMerchant(String account, String password, String userName, String tel, String address,
			String description, Blob photo, String fileName) {
		MerchantBean emp = null;
        Session session = factory.getCurrentSession();
        //先寫SQL
        String hql ="UPDATE MerchantBean m SET m.busAccount = :account, m.busPassword = :password, m.busName = :userName, m.busTel = :tel, m.busAddress = :address, m.busDescription = :description, m.busPhoto = :photo, m.busFileName = :fileName WHERE m.busAccount = :account";
        try{
            //準備執行SQL
            emp = (MerchantBean) session.createQuery(hql).setParameter("account", account).setParameter("password", password).setParameter("userName", userName).setParameter("tel", tel).setParameter("address", address).setParameter("description", description).setParameter("photo", photo).setParameter("fileName", fileName).getSingleResult();                
        } catch (NoResultException e) {
//	        e.printStackTrace();
	        emp = null;
		} 
        return emp ;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("MerchantDaoImpl_Hibernate類別不支援setConnection()方法");
	}
	
}
