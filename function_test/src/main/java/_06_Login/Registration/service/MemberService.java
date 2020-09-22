package _06_Login.Registration.service;

import java.util.List;

import _06_Login.Registration.model.MemberBean;

public interface MemberService {
	
	int saveMember(MemberBean mb);
	
	boolean accountExists(String account);
	
	MemberBean queryMember(String account);
	
	List<MemberBean> checkAccountPassword(String account, String password);
	
}
