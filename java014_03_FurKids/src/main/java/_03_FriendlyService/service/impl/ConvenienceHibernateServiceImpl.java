package _03_FriendlyService.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_Init.util.utils.HibernateUtils;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.dao.ConvenienceDao;
import _03_FriendlyService.dao.impl.ConvenienceDaoImp_H;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;

public class ConvenienceHibernateServiceImpl implements ConvenienceService{
	//ConvenienceDao介面定義方法，ConvenienceDaoImp_H實作介面
	ConvenienceDao cnDao;
	Integer busChildNo;
	SessionFactory factory;  //工廠
	
	
	public ConvenienceHibernateServiceImpl() {
		cnDao = new ConvenienceDaoImp_H();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insert(ConvenienceBean_H cb) {
		//使用當前的session,在commit或rollback後,會自動關閉
		Session session = factory.getCurrentSession(); 	
		//開啟交易
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			cnDao.insert(cb);
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
	}

	@Override
	public void update(ConvenienceBean_H cb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cnDao.update(cb);
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
	}

	@Override
	public void delete(ConvenienceBean_H cb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cnDao.delete(cb);
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
	}
	
	
	@Override
	public List<ConvenienceBean_H> getAllConvenience() {
		List<ConvenienceBean_H> beans = new ArrayList<>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = cnDao.getAllRestaurants();
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
		return beans;
	}
	
	//查詢所有上架的分店
	@Override
	public List<ConvenienceBean_H> getAllConvenience(String id) {
		List<ConvenienceBean_H> beans = new ArrayList<>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = cnDao.getAllRestaurants(id);
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
		return beans;
	}
	
	//查詢沒有上架的分店
	@Override
	public List<MerchantChildBean> getNotConvenience(String id) {
		List<MerchantChildBean> beans = new ArrayList<>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = cnDao.getNotRestaurants(id);
			tx.commit();
		} catch(Exception ex) {
			if(ex != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);			
		}
		return beans;
	}

	//依商家分店編號去撈該筆分店資料
	@Override
	public MerchantChildBean getBusChild(int busChildNo) {
		MerchantChildBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = cnDao.getBusChild(busChildNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

}
