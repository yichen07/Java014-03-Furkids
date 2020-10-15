package _03_FriendlyService.dao;

import java.util.List;

import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.model.ReservationBean;
import _03_FriendlyService.model.ReservationChildBean;

//定義Dao方法
public interface ReservationDao {
	
	void insert(ReservationBean rb);
	
	void insert(ReservationChildBean rb);
	
	void delete(ReservationChildBean rb);
	
	void delete(ReservationBean rb);
	Boolean getReservationBeanCusAccount(String account, int no);
	
	long getRecordCounts(String item);
	
	int getTotalPages(String item);
	
	ReservationBean getReservation(int no);
	
	List<ConvenienceBean_H> getPageViewConvenience(String item,int pageNo);
	
	List<ConvenienceBean_H> getViewConvenience(String item);
	
	List<ReservationBean> getReservationInfo(String account);
	
	List<ReservationBean> getReservationInfoForBus(String account);
	
	int getReservationBeanPK(ReservationBean rb);
}
