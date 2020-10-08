package _01_Member.Registration.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.util.List;

import _01_Member.Registration.model.MerchantBean;

public interface MerchantDao {
	
	public int saveMerchant(MerchantBean mb);
	
	public int updateMerchant(MerchantBean mb);
	
	public int deleteMerchant(String account);
	
	public boolean accountExists(String account);
	
	public MerchantBean queryMerchant(String account);
	
	public List<MerchantBean> queryAllMerchants();
	
	public MerchantBean checkAccountPassword(String account, String password);
//	public Object checkAccountPassword(String account, String password);

	public MerchantBean modifyMerchant(String account, String password, String userName, String tel, String address, String description, Blob photo, String fileName);
	
	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
}
