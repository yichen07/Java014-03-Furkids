package _03_FriendlyService.dao;

import java.util.List;

import _03_FriendlyService.model.ConvenienceBean_H;

//定義Dao方法
public interface ReservationDao {
	
	long getRecordCounts(String item);
	
	int getTotalPages(String item);
	
	List<ConvenienceBean_H> getPageViewConvenience(String item,int pageNo);
}
