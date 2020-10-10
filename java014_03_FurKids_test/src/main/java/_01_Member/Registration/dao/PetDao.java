package _01_Member.Registration.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import _01_Member.Registration.model.PetBean;

public interface PetDao {
	public int savePet(PetBean pet);
	
	public List<PetBean> queryAllPets(String account);
	
	public PetBean modifyPet(String account, String petName, String gender, Date birthday, String breed, String variety, Blob photo, String fileName);
	
	public void setConnection(Connection con); // 在Hibernate中不需要此方法。
}
