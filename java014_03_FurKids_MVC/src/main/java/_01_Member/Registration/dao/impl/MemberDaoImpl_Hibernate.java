package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.model.MemberBean;

@Repository
public class MemberDaoImpl_Hibernate implements MemberDao {
	
	@Autowired
	SessionFactory factory;
	
	public MemberDaoImpl_Hibernate() {
//		this.factory = HibernateUtils.getSessionFactory();
	}
	
	// 儲存MemberBean物件，將會員註冊資料mb新增到資料庫MembraneRegistration表格中。
	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	// 更新MemberBean物件，更新資料庫MembraneRegistration表格中的會員資料。
	@Override
	public int updateMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		if (mb != null && mb.getCusAccount() != null) {
			session.saveOrUpdate(mb);
			n++;			
		}
		return n;
	}
	
	// 刪除MemberBean物件，刪除資料庫MembraneRegistration表格中的會員資料。
	@Override
	public int deleteMember(String account) {
		int n = 0;
		MemberBean mb = queryMember(account);
		Session session = factory.getCurrentSession();
		if (mb != null) {
			session.delete(mb);
			n++;			
		}
		return n;
	}
	
	// 判斷參數CusAccount(會員帳號)是否已經被現有會員或商家使用，
	// 如果是，傳回true，表示此CusAccount(會員帳號)不能使用，
	// 否則傳回false，表示此CusAccount(會員帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean m WHERE m.cusAccount = :account";
		try {
			MemberBean mb = (MemberBean) session.createQuery(hql).setParameter("account", account).getSingleResult();
//			Object mb = session.createQuery(hql).setParameter("account", account).getSingleResult();
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

	
	// 由參數CusAccount(會員帳號)到MembraneRegistration表格中取得某個會員的所有資料，
	// 傳回值為一個MemberBean的物件；如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean m WHERE m.cusAccount = :account";
		try {
			mb = (MemberBean) session.createQuery(hql).setParameter("account", account).getSingleResult();
		} catch (NoResultException e) {
//			e.printStackTrace();
			mb = null;
		}
		return mb;
	}

	// 由MembraneRegistration表格中取得所有會員的資料，
	// 傳回值為一個MemberBean的List物件；如果找不到對應的會員資料，傳回值為null。
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> queryAllMembers() {
		List<MemberBean> mbs = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean";
		try {
			mbs = session.createQuery(hql).getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			mbs = null;
		}
		return mbs;
	}
	
	// 檢查使用者在登入時輸入的帳號密碼是否正確。
	// 如果正確傳回該帳號所對應的MemberBean物件，否則傳回null。
	@Override
	public MemberBean checkAccountPassword(String account, String password) {
//	public Object checkAccountPassword(String account, String password) {
		MemberBean mb = null;
//		Object mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean m WHERE m.cusAccount = :account AND m.cusPassword = :password";
		try {
			mb = (MemberBean) session.createQuery(hql).setParameter("account", account).setParameter("password", password).getSingleResult();
//			mb = session.createQuery(hql).setParameter("account", account).setParameter("password", password).getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			mb = null;
		}
		return mb;
	}
	
	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("MemberDaoImpl_Hibernate類別不支援setConnection()方法");
	}
	
}
