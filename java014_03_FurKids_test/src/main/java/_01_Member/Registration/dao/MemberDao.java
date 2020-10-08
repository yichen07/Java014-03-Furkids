package _01_Member.Registration.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import _01_Member.Registration.model.MemberBean;

public interface MemberDao {
	
	public int saveMember(MemberBean mb);
	
	public int updateMember(MemberBean mb);
	
	public int deleteMember(String account);
	
	public boolean accountExists(String account);
	
	public MemberBean queryMember(String account);
	
	public List<MemberBean> queryAllMembers();
	
	public MemberBean checkAccountPassword(String account, String password);
//	public Object checkAccountPassword(String account, String password);

	public MemberBean modifyMember(String account, String password, String userName, String nickName, String gender, Date birthday, String tel, String address, Blob photo, String fileName);
	
	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
}
