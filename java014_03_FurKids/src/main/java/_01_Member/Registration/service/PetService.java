package _01_Member.Registration.service;

import _01_Member.Registration.model.PetBean;

public interface PetService {
	
	int savePet(PetBean pet);
	
	PetBean queryPet(String account);
	
}
