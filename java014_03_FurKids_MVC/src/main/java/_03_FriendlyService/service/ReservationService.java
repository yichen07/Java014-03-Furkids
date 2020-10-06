package _03_FriendlyService.service;

import java.util.List;

import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.model.ReservationBean;
import _03_FriendlyService.model.ReservationChildBean;


public interface ReservationService {
	void insert(ReservationBean rb);
	
	void insert(ReservationChildBean rb);
	
	void delete(ReservationChildBean rb);
	
	void delete(ReservationBean rb);
	
	Boolean getReservationBeanCusAccount(String account, int no);
	
	List<ConvenienceBean_H> getPageViewConvenience(String item,int pageNo);
	
	List<ConvenienceBean_H> getViewConvenience(String item);
	
	List<ReservationBean> getReservationInfo(String account);
	
	int getTotalPages(String item);
	
	int getReservationBeanPK(ReservationBean rb);
}
