package _03_FriendlyService.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.dao.ConvenienceDao;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ConvenienceService;
@Service
public class ConvenienceHibernateServiceImpl implements ConvenienceService{
	//ConvenienceDao介面定義方法，ConvenienceDaoImp_H實作介面
	@Autowired
	ConvenienceDao cnDao;

//	SessionFactory factory;  //工廠
	
	
	public ConvenienceHibernateServiceImpl() {
//		cnDao = new ConvenienceDaoImp_H();
//		factory = HibernateUtils.getSessionFactory();
	}
	@Transactional
	@Override
	public void insert(ConvenienceBean_H cb) {
		//使用當前的session,在commit或rollback後,會自動關閉
//		Session session = factory.getCurrentSession(); 	
		//開啟交易
//		Transaction tx = null; 
//		try {
//			tx = session.beginTransaction();
			cnDao.insert(cb);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
	}
	
	@Transactional
	@Override
	public void update(ConvenienceBean_H cb) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			cnDao.update(cb);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
	}
	
	@Transactional
	@Override
	public void update(MerchantChildBean mb) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			cnDao.update(mb);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
//		
	}
	
	@Transactional
	@Override
	public void delete(ConvenienceBean_H cb) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			cnDao.delete(cb);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
	}
	
	@Transactional
	@Override
	public List<ConvenienceBean_H> getAllConvenience() {
		List<ConvenienceBean_H> beans = new ArrayList<>();
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			beans = cnDao.getAllRestaurants();
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
		return beans;
	}
	
	//查詢所有上架的分店
	@Transactional
	@Override
	public List<ConvenienceBean_H> getAllConvenience(String id) {
		List<ConvenienceBean_H> beans = new ArrayList<>();
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			beans = cnDao.getAllRestaurants(id);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
		return beans;
	}
	
	//查詢沒有上架的分店
	@Transactional
	@Override
	public List<MerchantChildBean> getNotConvenience(String id) {
		List<MerchantChildBean> beans = new ArrayList<>();
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			beans = cnDao.getNotRestaurants(id);
//			tx.commit();
//		} catch(Exception ex) {
//			if(ex != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);			
//		}
		return beans;
	}

	//依商家分店編號去撈該筆分店資料
	@Transactional
	@Override
	public MerchantChildBean getBusChild(int busChildNo) {
		MerchantChildBean bean = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			bean = cnDao.getBusChild(busChildNo);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return bean;
	}
	//新增服務和修改分店描述
	@Transactional
	@Override
	public void insertAndUpdate(ConvenienceBean_H cb, MerchantChildBean mcb) {
		cnDao.insert(cb);
		cnDao.update(mcb);
	}
	//依分店編號撈出該筆資料
	@Transactional
	@Override
	public ConvenienceBean_H getConvenience(int busChildNo) {
		ConvenienceBean_H bean = null;
		bean = cnDao.getConvenience(busChildNo);
		return bean;
	}
	//依商家帳號撈出該筆資料
	@Transactional
	@Override
	public MerchantBean getBus(String id) {
		MerchantBean bean = null;
		bean = cnDao.getBus(id);
		return bean;
	}
	@Transactional
	@Override
	public void Update(ConvenienceBean_H cb, MerchantChildBean mcb, MerchantBean mb) {
		cnDao.update(cb);
		cnDao.update(mcb);
		cnDao.update(mb);
	}
	
	//撈出計算過後的總頁數
	@Transactional
	@Override
	public int getTotalPages(String id) {
		int n = 0;
		n = cnDao.getTotalPages(id);
		return n;
	}
	//依帳號撈出該商家已上架的所有服務(一次撈8筆)
	@Transactional
	@Override
	public List<ConvenienceBean_H> getPageConvenience(String id, int pageNo) {
		List<ConvenienceBean_H> bean = null;
		bean = cnDao.getPageConvenience(id, pageNo);
		return bean;
	}
	
	//撈所有分店
	@Transactional
	@Override
	public List<MerchantChildBean> getBusChild(String id) {
		List<MerchantChildBean> bean = null;
		bean = cnDao.getBusChild(id);
		return bean;
	}
	
	@Transactional
	@Override
	public void Update(ConvenienceBean_H cb, MerchantChildBean mcb) {
		cnDao.update(mcb);
		cnDao.update(cb);
		
	}
	


	

}
