package _03_FriendlyService.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _03_FriendlyService.dao.ReservationDao;
import _03_FriendlyService.model.ConvenienceBean_H;

@Repository
public class ReservationDaoImp implements ReservationDao{
	
	public int totalPages = -1;
	
	 @Autowired
	SessionFactory factory;
	
	 //查詢總筆數
	@Override
	public long getRecordCounts(String item) {	
			long count = 0; // 必須使用 long 型態
			String hql = "SELECT count(*) FROM ConvenienceBean_H WHERE conItem = :item";
			Session session = factory.getCurrentSession();
			count = (Long)session.createQuery(hql).setParameter("item", item).getSingleResult();
			System.out.println("count = " + count);
			return count;
		
	}
	//設定總頁數
	@Override
	public int getTotalPages(String item) {
		totalPages = (int) (Math.ceil(getRecordCounts(item) / 8.0));
		return totalPages;
	}
	//依上架類型撈出所有商家已上架的服務(一次撈8筆)
	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenienceBean_H> getPageViewConvenience(String item, int pageNo) {
		List<ConvenienceBean_H> list = new ArrayList<>();
		String hql = "FROM ConvenienceBean_H m where m.conItem = :mitem";
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * 8;
		list = session.createQuery(hql).setParameter("mitem", item)
									   .setFirstResult(startRecordNo)
									   .setMaxResults(8)
									   .getResultList();
		return list;
	
	}


	

}
