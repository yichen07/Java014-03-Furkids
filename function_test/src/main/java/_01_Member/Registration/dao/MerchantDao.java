package _01_Member.Registration.dao;

import java.sql.Connection;

import _01_Member.Registration.model.MerchantBean;

public interface MerchantDao {
	
	public int saveMerchant(MerchantBean mb);
	
	public boolean accountExists(String account);
	
	public MerchantBean queryMerchant(String account);
	
	public MerchantBean checkAccountPassword(String account, String password);

	public void setConnection(Connection con);
}
