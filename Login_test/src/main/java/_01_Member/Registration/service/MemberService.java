package _01_Member.Registration.service;

import java.util.List;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;

public interface MemberService {
	
	int saveMember(MemberBean mb);
	
	boolean accountExists(String account);
	
	MemberBean queryMember(String account);
	
	List<MemberBean> checkAccountPassword(String account, String password);
	
	
	int savePet(PetBean pet);
	
	PetBean queryPet(String account);
	
}
