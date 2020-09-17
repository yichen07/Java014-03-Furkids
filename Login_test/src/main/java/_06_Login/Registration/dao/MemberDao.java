package _06_Login.Registration.dao;

import java.sql.Connection;
import java.util.List;

import _06_Login.Registration.model.MemberBean;

public interface MemberDao {
	
	public int saveMember(_06_Login.Registration.model.MemberBean mb);
	
	public boolean accountExists(String account);
	
	public MemberBean queryMember(String account);
	
	List<MemberBean> checkAccountPassword(String account, String password);

	public void setConnection(Connection con);
}
