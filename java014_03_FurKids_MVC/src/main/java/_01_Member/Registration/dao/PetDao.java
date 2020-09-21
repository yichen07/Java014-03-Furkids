package _01_Member.Registration.dao;

import java.sql.Connection;

import _01_Member.Registration.model.PetBean;

public interface PetDao {
	public int savePet(PetBean pet);
	
	public PetBean queryPet(String account);
	
	public void setConnection(Connection con);
}
