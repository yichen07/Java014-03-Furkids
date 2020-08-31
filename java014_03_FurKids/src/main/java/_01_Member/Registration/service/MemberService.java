package _01_Member.Registration.service;

import _01_Member.Registration.model.MemberBean;

public interface MemberService {
	
	int saveMember(MemberBean mb);
	
	boolean accountExists(String account);
	
	MemberBean queryMember(String account);
	
	MemberBean checkAccountPassword(String account, String password);
	
}
