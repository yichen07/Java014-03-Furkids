package _01_Member.Registration.service.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	MemberDao mdao;
	@Autowired
	public void setDao(MemberDao mdao) {
		this.mdao = mdao;
	}
	
	PetDao pdao;
	@Autowired
	public void setDao(PetDao pdao) {
		this.pdao = pdao;
	}
	
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public MemberServiceImpl() {
	}

	@Transactional
	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
			mdao.saveMember(mb);
			count++;
		return count;
	}
	
	@Transactional
	@Override
	public int updateMember(MemberBean mb) {
		int count = 0;
			mdao.updateMember(mb);
			count++;
		return count;
	}
	
	@Transactional
	@Override
	public int deleteMember(String account) {
		int count = 0;
			mdao.deleteMember(account);
			count++;
		return count;
	}
	

	@Transactional
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
			exist = mdao.accountExists(account);
		return exist;
	}

	@Transactional
	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
			mb = mdao.queryMember(account);
		return mb;
	}

	@Transactional
	@Override
	public List<MemberBean> queryAllMembers() {
		return mdao.queryAllMembers();
	}
	
	@Transactional
	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		MemberBean mb = null;
			mb = mdao.checkAccountPassword(account, password);
		return mb;
	}
	
	@Transactional
	@Override
	public int savePet(PetBean pet) {
		int count = 0;
			pdao.savePet(pet);
			count++;
		return count;
	}

	@Transactional
	@Override
	public List<PetBean> queryAllPets(String account) {
		return pdao.queryAllPets(account);
	}

	@Transactional
	@Override
	public PetBean queryPet(Integer id) {
		return pdao.queryPet(id);
	}
	
	@Transactional
	@Override
	public int updatePet(PetBean pet) {
		int count = 0;
			pdao.updatePet(pet);
			count++;
		return count;
	}

}
