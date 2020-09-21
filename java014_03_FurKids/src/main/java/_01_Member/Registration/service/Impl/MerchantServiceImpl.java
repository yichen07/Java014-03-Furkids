package _01_Member.Registration.service.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.dao.impl.MerchantChildDaoImpl_Hibernate;
import _01_Member.Registration.dao.impl.MerchantDaoImpl_Hibernate;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MerchantService;
import _01_Member.util.HibernateUtils;

@Service
public class MerchantServiceImpl implements MerchantService {

	MerchantDao mdao;
	@Autowired
	public void setDao(MerchantDao mdao) {
		this.mdao = mdao;
	}
	
	MerchantChildDao mcdao;
	@Autowired
	public void setDao(MerchantChildDao mcdao) {
		this.mcdao = mcdao;
	}
	
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public MerchantServiceImpl() {
//		this.mdao = new MerchantDaoImpl_Hibernate();
//		this.mcdao = new MerchantChildDaoImpl_Hibernate();
//		this.factory = HibernateUtils.getSessionFactory();
	}

	@Transactional
	@Override
	public int saveMerchant(MerchantBean mb) {
		int count = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			mdao.saveMerchant(mb);
			count++;
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return count;
	}
	
	@Transactional
	@Override
	public int saveMerchantChild(MerchantChildBean mcb) {
		int count = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			mcdao.saveMerchantChild(mcb);
			count++;
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return count;
	}
	
	@Transactional
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			exist = mdao.accountExists(account);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return exist;
	}

	@Transactional
	@Override
	public boolean merchantChildExists(String account, String address) {
		boolean exist = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			exist = mcdao.merchantChildExists(account, address);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return exist;
	}
	
	@Transactional
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			mb = mdao.queryMerchant(account);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return mb;
	}
	
	@Transactional
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		MerchantChildBean mcb = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			mcb = mcdao.queryMerchantChild(account);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return mcb;
	}
	
	@Transactional
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
//	public Object checkAccountPassword(String account, String password) {
		MerchantBean mb = null;
//		Object mb = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			mb = mdao.checkAccountPassword(account, password);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		return mb;
	}

}
