package _01_Member.Registration.service;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;

public interface MemberService {
	
	int saveMember(MemberBean mb);
	
	boolean accountExists(String account);
	
	MemberBean queryMember(String account);
	
	MemberBean checkAccountPassword(String account, String password);
//	Object checkAccountPassword(String account, String password);
	
	
	int savePet(PetBean pet);
	
	PetBean queryPet(String account);
	
}
