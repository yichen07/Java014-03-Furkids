package _06_Login.Registration.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _06_Login.Registration.dao.MerchantChildDao;
import _06_Login.Registration.dao.MerchantDao;
import _06_Login.Registration.model.MerchantBean;
import _06_Login.Registration.model.MerchantChildBean;
import _06_Login.Registration.service.MerchantService;
@Service
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	MerchantDao mdao;
	MerchantChildDao mcdao;
	public MerchantServiceImpl() {
//		this.mdao = new MerchantDaoImpl_Jdbc();
//		this.mcdao = new MerchantChildDaoImpl_Jdbc();
	}
	@Transactional
	@Override
	public int saveMerchant(MerchantBean mb) {
		return mdao.saveMerchant(mb);
	}
	@Transactional
	@Override
	public int saveMerchantChild(MerchantChildBean mb) {
		return mcdao.saveMerchantChild(mb);
	}
	@Transactional	
	@Override
	public boolean accountExists(String account) {
		return mdao.accountExists(account);
	}
	@Transactional
	@Override
	public MerchantBean queryMerchant(String account) {
		return mdao.queryMerchant(account);
	}
	@Transactional	
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		return mcdao.queryMerchantChild(account);
	}
	@Transactional	
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
		MerchantBean mb = mdao.checkAccountPassword(account, password);
		return mb;
	}
	@Transactional
	@Override
	public boolean merchantChildExists(String account, String address) {
		return mcdao.merchantChildExists(account, address);
	}
}
