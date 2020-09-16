package _03_FriendlyService.dao;

import java.util.List;
import java.util.Map;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;


//定義Dao方法
public interface ConvenienceDao {
	void insert(ConvenienceBean_H cb);

	void update(ConvenienceBean_H cb);
	
	void update(MerchantChildBean mcb);
	
	void update(MerchantBean mb);
	
	void delete(ConvenienceBean_H cb);
	
	List<ConvenienceBean_H> getAllRestaurants();
	
	List<ConvenienceBean_H> getAllRestaurants(String id);
	
	List<MerchantChildBean> getNotRestaurants(String id);
	
	MerchantBean getBus(String id);
	
	MerchantChildBean getBusChild(int busChildNo);
	
	ConvenienceBean_H getConvenience(int busChildNo);
	
	long getRecordCounts();
	
	List<ConvenienceBean_H> getPageConvenience(String id,int pageNo);
	
	int getTotalPages();
	
}
