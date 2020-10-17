package _03_FriendlyService.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_Member.Registration.model.MemberBean;
import _03_FriendlyService.dao.ReservationDao;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.model.ReservationBean;
import _03_FriendlyService.model.ReservationChildBean;

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
	@Override
	public void insert(ReservationBean rb) {
		Session session = factory.getCurrentSession();
		session.save(rb);
		
	}
	//查PK
	@Override
	public int getReservationBeanPK(ReservationBean rb,MemberBean mb) {
		int n = 0;
		String hql = "Select resID FROM ReservationBean where busChildNo = :busChildNo and cusAccount = :cusAccount";
		Session session = factory.getCurrentSession();
		n = (int) session.createQuery(hql).setParameter("busChildNo", rb.getBusChildNo())
										  .setParameter("cusAccount", mb.getCusAccount()).getSingleResult();
		return n;
	}
	@Override
	public void insert(ReservationChildBean rb) {
		Session session = factory.getCurrentSession();
		session.save(rb);
	}
	
	//查會員帳號

	@Override
	public Boolean getReservationBeanCusAccount(String account, int no) {
		Boolean id = true;
		String hql = "Select cusAccount FROM ReservationBean where busChildNo = :busChildNo and cusAccount = :account";
		Session session = factory.getCurrentSession();
		try {
			String act = (String) session.createQuery(hql).setParameter("busChildNo", no).
											setParameter("account" ,account).getSingleResult();
			
		}catch(NoResultException e) {
			id = false;
		}
		return id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenienceBean_H> getViewConvenience(String item) {
		List<ConvenienceBean_H> list = new ArrayList<>();
		String hql = "FROM ConvenienceBean_H m where m.conItem = :mitem";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("mitem", item).getResultList();
		return list;
	
	}
	//以會員帳號撈預約資料
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationBean> getReservationInfo(String account) {
		List<ReservationBean> list = new ArrayList<>();
		String hql = "FROM ReservationBean where cusAccount = :account";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("account", account).getResultList();	
		return list;
	}
	@Override
	public void delete(ReservationChildBean rb) {
		Session session = factory.getCurrentSession();
		session.delete(rb);
		
	}
	@Override
	public void delete(ReservationBean rb) {
		Session session = factory.getCurrentSession();
		session.delete(rb);
	}
	@Override
	public ReservationBean getReservation(int no) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ReservationBean where resId = :id";
		ReservationBean rb = (ReservationBean) session.createQuery(hql).setParameter("id", no).getSingleResult();
		return rb;
	}
	//以商家帳號撈預約資料
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationBean> getReservationInfoForBus(String account) {
		List<ReservationBean> list = new ArrayList<>();
		String hql = "FROM ReservationBean where conAccount = :account";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("account", account).getResultList();	
		return list;
	}


	
	

}
