package _01_Member.Registration.service.Impl;

import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.dao.impl.MerchantDaoImpl_Jdbc;
import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.service.MerchantService;

public class MerchantServiceImpl implements MerchantService {

	MerchantDao dao;
	public MerchantServiceImpl() {
		this.dao = new MerchantDaoImpl_Jdbc();
	}

	@Override
	public int saveMerchant(MerchantBean mb) {
		return dao.saveMerchant(mb);
	}

	@Override
	public boolean accountExists(String account) {
		return dao.accountExists(account);
	}

	@Override
	public MerchantBean queryMerchant(String account) {
		return dao.queryMerchant(account);
	}

	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
		MerchantBean mb = dao.checkAccountPassword(account, password);
		return mb;
	}

}
