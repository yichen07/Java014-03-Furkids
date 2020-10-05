package _01_Member.Registration.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	public MemberServiceImpl() {
		//this.dao = new MemberDaoImpl();
	}
	@Transactional
	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		dao.saveMember(mb);
		n++;
		return n;
	}
	@Transactional
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		exist = dao.accountExists(account);
		return exist;
	}
	@Transactional
	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
		mb = dao.queryMember(account);
		return mb;
	}
	@Transactional
	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		MemberBean beans = null;
		beans = dao.checkAccountPassword(account, password);
		return beans;
	}
	@Transactional
	@Override
	public int savePet(PetBean pet) {
		int count = 0;
		return count;
	}
	@Transactional
	@Override
	public PetBean queryPet(String account) {
		PetBean pet = null;
		return pet;
	}

}
