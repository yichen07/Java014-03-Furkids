package _02_ShoppingSystem.CommodityList.dao;

import java.util.List;

import _02_ShoppingSystem.CommodityList.model.MerchantBean;

public interface MerchantDao {

	//撈店家全部
	List<MerchantBean> getMerchant();
	
	//
	MerchantBean getMerchantByBusAccount();
	
	int getSelected();
	
	String getBusAccount();
	
	
	
}
