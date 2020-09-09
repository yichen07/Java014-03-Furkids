package _01_Member.Registration.service.Impl;

import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.dao.impl.PetDaoImpl_Jdbc;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.PetService;

public class PetServiceImpl implements PetService {

	PetDao dao;
	public PetServiceImpl() {
		this.dao = new PetDaoImpl_Jdbc();
	}
	
	@Override
	public int savePet(PetBean pet) {
		return dao.savePet(pet);
	}

	@Override
	public PetBean queryPet(String account) {
		return dao.queryPet(account);
	}

}
