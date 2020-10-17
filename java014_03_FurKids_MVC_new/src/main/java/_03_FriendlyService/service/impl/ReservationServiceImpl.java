package _03_FriendlyService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.model.MemberBean;
import _03_FriendlyService.dao.ReservationDao;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.model.ReservationBean;
import _03_FriendlyService.model.ReservationChildBean;
import _03_FriendlyService.service.ReservationService;
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	ReservationDao resDao;
	
	public ReservationServiceImpl() {

	}

	
	//撈出計算過後的總頁數
	@Override
	public int getTotalPages(String item) {
		int n = 0;
		n = resDao.getTotalPages(item);
		return n;
	}

	//依上架類型撈出所有商家已上架的服務(一次撈8筆)
	@Override
	public List<ConvenienceBean_H> getPageViewConvenience(String item, int pageNo) {
		return resDao.getPageViewConvenience(item, pageNo);
	}


	@Override
	public void insert(ReservationBean rb) {
		resDao.insert(rb);
		
	}


	@Override
	public int getReservationBeanPK(ReservationBean rb,MemberBean mb) {
		int n = 0;
		n = resDao.getReservationBeanPK(rb,mb);
		return n;
	}


	@Override
	public void insert(ReservationChildBean rb) {
		resDao.insert(rb);
		
	}


	@Override
	public Boolean getReservationBeanCusAccount(String account, int no) {
		return resDao.getReservationBeanCusAccount(account, no);
	}


	@Override
	public List<ConvenienceBean_H> getViewConvenience(String item) {
		return resDao.getViewConvenience(item);
	}


	@Override
	public List<ReservationBean> getReservationInfo(String account) {
		return resDao.getReservationInfo(account);
	}


	@Override
	public void delete(ReservationChildBean rb) {
		resDao.delete(rb);
		
	}


	@Override
	public void delete(ReservationBean rb) {
		resDao.delete(rb);
		
	}


	@Override
	public ReservationBean getReservation(int no) {
		return resDao.getReservation(no);
	}


	@Override
	public List<ReservationBean> getReservationInfoForBus(String account) {
		return resDao.getReservationInfoForBus(account);
	}


	

	


	

}
