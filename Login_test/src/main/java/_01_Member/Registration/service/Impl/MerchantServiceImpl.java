package _01_Member.Registration.service.Impl;

import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.dao.impl.MerchantChildDaoImpl_Jdbc;
import _01_Member.Registration.dao.impl.MerchantDaoImpl_Jdbc;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;
import _01_Member.Registration.service.MerchantService;

public class MerchantServiceImpl implements MerchantService {

	MerchantDao mdao;
	MerchantChildDao mcdao;
	public MerchantServiceImpl() {
		this.mdao = new MerchantDaoImpl_Jdbc();
		this.mcdao = new MerchantChildDaoImpl_Jdbc();
	}

	@Override
	public int saveMerchant(MerchantBean mb) {
		return mdao.saveMerchant(mb);
	}
	
	@Override
	public int saveMerchantChild(MerchantChildBean mb) {
		return mcdao.saveMerchantChild(mb);
	}
	
	@Override
	public boolean accountExists(String account) {
		return mdao.accountExists(account);
	}

	@Override
	public MerchantBean queryMerchant(String account) {
		return mdao.queryMerchant(account);
	}
	
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		return mcdao.queryMerchantChild(account);
	}
	
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
		MerchantBean mb = mdao.checkAccountPassword(account, password);
		return mb;
	}

	@Override
	public boolean merchantChildExists(String account, String address) {
		return mcdao.merchantChildExists(account, address);
	}
}
