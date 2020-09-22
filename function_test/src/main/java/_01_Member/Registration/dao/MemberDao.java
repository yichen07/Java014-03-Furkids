package _01_Member.Registration.dao;

import java.sql.Connection;

import _01_Member.Registration.model.MemberBean;

public interface MemberDao {
	
	public int saveMember(MemberBean mb);
	
	public boolean accountExists(String account);
	
	public MemberBean queryMember(String account);
	
	public MemberBean checkAccountPassword(String account, String password);
//	public Object checkAccountPassword(String account, String password);

	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
}
