package _01_Member.Registration.service.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	MerchantDao mdao;
	@Autowired
	public void setDao(MerchantDao mdao) {
		this.mdao = mdao;
	}
	
	MerchantChildDao mcdao;
	@Autowired
	public void setDao(MerchantChildDao mcdao) {
		this.mcdao = mcdao;
	}
	
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public MerchantServiceImpl() {

	}

	@Transactional
	@Override
	public int saveMerchant(MerchantBean mb) {
		int count = 0;
			mdao.saveMerchant(mb);
			count++;
		return count;
	}
	
	@Transactional
	@Override
	public int updateMerchant(MerchantBean mb) {
		int count = 0;
			mdao.updateMerchant(mb);
			count++;
		return count;
	}
	
	@Transactional
	@Override
	public int deleteMerchant(String account) {
		int count = 0;
			mdao.deleteMerchant(account);
			count++;
		return count;
	}
	
	
	@Transactional
	@Override
	public int saveMerchantChild(MerchantChildBean mcb) {
		int count = 0;
			mcdao.saveMerchantChild(mcb);
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
	public boolean merchantChildExists(String account, String address) {
		boolean exist = false;
			exist = mcdao.merchantChildExists(account, address);
		return exist;
	}
	
	@Transactional
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
			mb = mdao.queryMerchant(account);
		return mb;
	}
	
	@Transactional
	@Override
	public List<MerchantBean> queryAllMerchants() {
		return mdao.queryAllMerchants();
	}
	
	@Transactional
	@Override
	public List<MerchantChildBean> queryAllMerchantChilds(String account) {
		return mcdao.queryAllMerchantChilds(account);
	}
	
	@Transactional
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
		MerchantBean mb = null;
			mb = mdao.checkAccountPassword(account, password);
		return mb;
	}
	
	@Transactional
	@Override
	public MerchantChildBean queryMerchantChild(Integer busChildNo) {
		return mcdao.queryMerchantChild(busChildNo);
	}
	
	@Transactional
	@Override
	public int updateMerchantChild(MerchantChildBean mcb) {
		int count = 0;
			mcdao.updateMerchantChild(mcb);
			count++;
		return count;
	}

}
