package _01_Member.Registration.dao;

import java.sql.Connection;
import java.util.List;

import _01_Member.Registration.model.MemberBean;

public interface MemberDao {
	
	public int saveMember(MemberBean mb);
	
	public boolean accountExists(String account);
	
	public MemberBean queryMember(String account);
	
	public List<MemberBean> checkAccountPassword(String account, String password);

	public void setConnection(Connection con);
}
