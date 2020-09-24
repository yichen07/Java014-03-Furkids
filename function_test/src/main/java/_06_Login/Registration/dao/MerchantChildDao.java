package _06_Login.Registration.dao;

import java.sql.Connection;

import _06_Login.Registration.model.MerchantChildBean;

public interface MerchantChildDao {
	
	public int saveMerchantChild(MerchantChildBean mb);
	
	public MerchantChildBean queryMerchantChild(String account);
	
	public boolean merchantChildExists(String account, String address);
	
	public void setConnection(Connection con);
}
