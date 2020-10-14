package _01_Member.Registration.dao;

import java.sql.Connection;
import java.util.List;

import _01_Member.Registration.model.MerchantChildBean;

public interface MerchantChildDao {
	
	public int saveMerchantChild(MerchantChildBean mb);
	
	public List<MerchantChildBean> queryAllMerchantChilds(String account);
	
	public boolean merchantChildExists(String account, String address);
	
	public int updateMerchantChild(MerchantChildBean mcb);
	
	public MerchantChildBean queryMerchantChild(Integer busChildNo);
	
	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
}
