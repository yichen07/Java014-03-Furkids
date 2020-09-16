package _01_Member.Registration.service.Impl;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.dao.impl.MemberDaoImpl_Jdbc;
import _01_Member.Registration.dao.impl.PetDaoImpl_Jdbc;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao mdao;
	PetDao pdao;
	
	public MemberServiceImpl() {
		this.mdao = new MemberDaoImpl_Jdbc();
		this.pdao = new PetDaoImpl_Jdbc();
	}

	@Override
	public int saveMember(MemberBean mb) {
		return mdao.saveMember(mb);
	}

	@Override
	public boolean accountExists(String account) {
		return mdao.accountExists(account);
	}

	@Override
	public MemberBean queryMember(String account) {
		return mdao.queryMember(account);
	}

	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		MemberBean mb = mdao.checkAccountPassword(account, password);
		return mb;
	}
	
	
	@Override
	public int savePet(PetBean pet) {
		return pdao.savePet(pet);
	}

	@Override
	public PetBean queryPet(String account) {
		return pdao.queryPet(account);
	}

}
