package _03_FriendlyService.service;

import java.util.List;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;


public interface ConvenienceService {
	
	void insert(ConvenienceBean_H cb);

	void update(ConvenienceBean_H cb);

	void update(MerchantChildBean mb);
	
	void delete(ConvenienceBean_H cb);
	
	void insertAndUpdate(ConvenienceBean_H cb, MerchantChildBean mcb);
	
	void Update(ConvenienceBean_H cb, MerchantChildBean mcb, MerchantBean mb);
	
	
	
	List<ConvenienceBean_H> getAllConvenience();
	
	List<ConvenienceBean_H> getAllConvenience(String id);
	
	List<MerchantChildBean> getNotConvenience(String id);
	
	List<MerchantChildBean> getBusChild(String id);
	
	MerchantBean getBus(String id);

	MerchantChildBean getBusChild(int busChildNo);
	
	ConvenienceBean_H getConvenience(int busChildNo);
	
	List<ConvenienceBean_H> getPageConvenience(String id,int pageNo);
	
	int getTotalPages(String id);
}
