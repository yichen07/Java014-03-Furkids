package _01_Member.Registration.dao;

import java.sql.Connection;
import java.util.List;

import _01_Member.Registration.model.PetBean;

public interface PetDao {
	public int savePet(PetBean pet);
	
	public int updatePet(PetBean pet);
	
	public List<PetBean> queryAllPets(String account);
	
	public PetBean queryPet(Integer id);
	
	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
	
}
