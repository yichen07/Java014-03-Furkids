package _06_Login.Registration.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _06_Login.Registration.dao.MemberDao;
import _06_Login.Registration.model.MemberBean;
import _06_Login.Registration.service.MemberService;
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
		return dao.saveMember(mb);
	}
	@Transactional
	@Override
	public boolean accountExists(String account) {
		return dao.accountExists(account);
	}
	@Transactional
	@Override
	public MemberBean queryMember(String account) {
		return dao.queryMember(account);
	}
	@Transactional
	@Override
	public List<MemberBean> checkAccountPassword(String account, String password) {
		List<MemberBean> beans = new ArrayList<>();
		beans = dao.checkAccountPassword(account, password);
		return beans;
	}

}
