package _01_Member.Registration.service.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.dao.impl.MerchantChildDaoImpl_Hibernate;
import _01_Member.Registration.dao.impl.MerchantDaoImpl_Hibernate;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MerchantService;
import _01_Member.util.HibernateUtils;

public class MerchantServiceImpl implements MerchantService {

	MerchantDao mdao;
	MerchantChildDao mcdao;
	SessionFactory factory;
	
	public MerchantServiceImpl() {
		this.mdao = new MerchantDaoImpl_Hibernate();
		this.mcdao = new MerchantChildDaoImpl_Hibernate();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int saveMerchant(MerchantBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mdao.saveMerchant(mb);
			count++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return count;
	}
	
	@Override
	public int saveMerchantChild(MerchantChildBean mcb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mcdao.saveMerchantChild(mcb);
			count++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return count;
	}
	
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = mdao.accountExists(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return exist;
	}

	@Override
	public boolean merchantChildExists(String account, String address) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = mcdao.merchantChildExists(account, address);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return exist;
	}
	
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = mdao.queryMerchant(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}
	
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		MerchantChildBean mcb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mcb = mcdao.queryMerchantChild(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mcb;
	}
	
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
//	public Object checkAccountPassword(String account, String password) {
		MerchantBean mb = null;
//		Object mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = mdao.checkAccountPassword(account, password);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}

}
