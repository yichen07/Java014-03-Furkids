package _01_Member.Registration.service.Impl;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.dao.impl.MemberDaoImpl_Jdbc;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao dao;
	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl_Jdbc();
	}

	@Override
	public int saveMember(MemberBean mb) {
		return dao.saveMember(mb);
	}

	@Override
	public boolean accountExists(String account) {
		return dao.accountExists(account);
	}

	@Override
	public MemberBean queryMember(String account) {
		return dao.queryMember(account);
	}

	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		MemberBean mb = dao.checkAccountPassword(account, password);
		return mb;
	}

}
