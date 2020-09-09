package _03_FriendlyService.dao;

import java.util.List;

import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;


//定義Dao方法
public interface ConvenienceDao {
	void insert(ConvenienceBean_H cb);

	void update(ConvenienceBean_H cb);
	
	void update(MerchantChildBean mb);
	
	void delete(ConvenienceBean_H cb);
	
	List<ConvenienceBean_H> getAllRestaurants();
	
	List<ConvenienceBean_H> getAllRestaurants(String id);
	
	List<MerchantChildBean> getNotRestaurants(String id);
	// 依busChildNo來查詢單筆分店記錄
	MerchantChildBean getBusChild(int busChildNo);
}
