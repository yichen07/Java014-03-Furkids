package _03_FriendlyService.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00_Init.util.utils.HibernateUtils;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.dao.ConvenienceDao;
import _03_FriendlyService.model.ConvenienceBean_H;

@Repository
public class ConvenienceDaoImp_H implements ConvenienceDao{
	
	public int totalPages = -1;
	
	 @Autowired
	SessionFactory factory;
	
	public ConvenienceDaoImp_H() {
//		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insert(ConvenienceBean_H cb) {
		Session session = factory.getCurrentSession();
		session.save(cb);
	}

	@Override
	public void update(ConvenienceBean_H cb) {
		Session session = factory.getCurrentSession();
		session.merge(cb);
	}
	
	@Override
	public void update(MerchantChildBean mcb) {
		Session session = factory.getCurrentSession();
		session.merge(mcb);
		
	}
	
	@Override
	public void update(MerchantBean mb) {
		Session session = factory.getCurrentSession();
		session.merge(mb);
		
	}

	@Override
	public void delete(ConvenienceBean_H cb) {
		Session session = factory.getCurrentSession();
		session.delete(cb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenienceBean_H> getAllRestaurants() {
		//使用當前的session,在commit或rollback後,會自動關閉
		Session session = factory.getCurrentSession();
		//SELECT FROM (hibernate控制的類別bean)
		String hql = "FROM ConvenienceBean_H";
		//用一個List去存撈回來的資料
		List<ConvenienceBean_H> list = new ArrayList<>();
		//搜尋條件(hql)，如果查詢多筆要用getResultList
		list = session.createQuery(hql).getResultList();
		//回傳list
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenienceBean_H> getAllRestaurants(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ConvenienceBean_H m where m.busAccount = :mid";
		List<ConvenienceBean_H> list = new ArrayList<>();
		list = session.createQuery(hql).setParameter("mid", id).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantChildBean> getNotRestaurants(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM MerchantChildBean m where m.busAccount = :mid";  
		// and m.convenienceBean_H is null
		List<MerchantChildBean> list = new ArrayList<>();
		List<MerchantChildBean> list2 = new ArrayList<>();
		list = session.createQuery(hql).setParameter("mid", id).getResultList();
		MerchantChildBean abc;
		System.out.println("======================");
		for(MerchantChildBean o : list) {
			if(o.getConvenienceBean_H() == null) {
				abc = session.get(o.getClass(), o.getBusChildNo());
				list2.add(abc);
			}
		}
		System.out.println("======================");
		return list2;
	}
	// 依busChildNo來查詢單筆分店記錄
	@Override
	public MerchantChildBean getBusChild(int busChildNo) {
		MerchantChildBean bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(MerchantChildBean.class, busChildNo);
		return bean;
	}
	// 依busChildNo來查詢單筆上架記錄
	@Override
	public ConvenienceBean_H getConvenience(int busChildNo) {
		ConvenienceBean_H bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(ConvenienceBean_H.class, busChildNo);
		return bean;
	}

	@Override
	public MerchantBean getBus(String id) {
		MerchantBean bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(MerchantBean.class, id);
		return bean;
	}
	//撈出該商家已上架的服務(一次撈8筆)
	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenienceBean_H> getPageConvenience(String id,int pageNo) {
		List<ConvenienceBean_H> list = new ArrayList<>();
		String hql = "FROM ConvenienceBean_H m where m.busAccount = :mid";
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * 8;
		list = session.createQuery(hql).setParameter("mid", id)
									   .setFirstResult(startRecordNo)
									   .setMaxResults(8)
									   .getResultList();
		return list;
	}
	
	//查詢總筆數
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM ConvenienceBean_H";
		Session session = factory.getCurrentSession();
		count = (Long)session.createQuery(hql).getSingleResult();
		return count;
	}
	
	//計算總共幾頁
	@Override
	public int getTotalPages() {
		totalPages = (int) (Math.ceil(getRecordCounts() / 8.0));
		return totalPages;
	}

	

	
	
	

	

}
