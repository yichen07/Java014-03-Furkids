package _01_Member.Registration.service;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;

public interface MerchantService {
	
	int saveMerchant(MerchantBean mb);
	
	boolean accountExists(String account);
	
	MerchantBean queryMerchant(String account);
	
	MerchantBean checkAccountPassword(String account, String password);
//	Object checkAccountPassword(String account, String password);
	
	
	int saveMerchantChild(MerchantChildBean mb);
	
	MerchantChildBean queryMerchantChild(String account);
	
	boolean merchantChildExists(String account, String address);
	
}
