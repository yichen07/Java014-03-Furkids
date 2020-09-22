package _06_Login.Registration.service;

import _06_Login.Registration.model.MerchantBean;
import _06_Login.Registration.model.MerchantChildBean;

public interface MerchantService {
	
	int saveMerchant(MerchantBean mb);
	
	boolean accountExists(String account);
	
	MerchantBean queryMerchant(String account);
	
	MerchantBean checkAccountPassword(String account, String password);
	
	
	
	int saveMerchantChild(MerchantChildBean mb);
	
	MerchantChildBean queryMerchantChild(String account);
	
	boolean merchantChildExists(String account, String address);
	
}
