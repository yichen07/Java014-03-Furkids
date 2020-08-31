package _01_Member.Registration.service;

import _01_Member.Registration.model.MerchantBean;

public interface MerchantService {
	
	int saveMerchant(MerchantBean mb);
	
	boolean accountExists(String account);
	
	MerchantBean queryMerchant(String account);
	
	MerchantBean checkAccountPassword(String account, String password);
	
}
