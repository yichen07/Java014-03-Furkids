package _03_FriendlyService.service;

import java.util.List;

import _01_Member.Registration.model.MerchantChildBean;
import _03_FriendlyService.model.ConvenienceBean_H;


public interface ConvenienceService {
	
	void insert(ConvenienceBean_H cb);

	void update(ConvenienceBean_H cb);

	void delete(ConvenienceBean_H cb);
	
	List<ConvenienceBean_H> getAllConvenience();
	
	List<ConvenienceBean_H> getAllConvenience(String id);
	
	List<MerchantChildBean> getNotConvenience(String id);
	//依商家分店編號去撈該筆分店資料
	MerchantChildBean getBusChild(int busChildNo);
}
