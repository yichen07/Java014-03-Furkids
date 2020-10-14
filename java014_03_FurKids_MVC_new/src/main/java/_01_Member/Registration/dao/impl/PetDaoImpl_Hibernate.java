package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.model.PetBean;


@Repository
public class PetDaoImpl_Hibernate implements PetDao {
	
	SessionFactory factory;
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public PetDaoImpl_Hibernate() {
//		this.factory = HibernateUtils.getSessionFactory();
	}

	// 儲存PetBean物件，將會員新增寵物資料pet新增到資料庫PetRegistration表格中。
	@Override
	public int savePet(PetBean pet) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(pet);
		n++;
		return n;
	}

	
	// 由參數petID(寵物編號)到PetRegistration表格中取得某個會員的特定寵物資料，
	// 傳回值為一個PetBean的物件。
	@Override
	public PetBean queryPet(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PetBean p WHERE p.petID = :petID";
		PetBean pet = (PetBean) session.createQuery(hql).setParameter("petID", id).getSingleResult();
		return pet;
	}
	
	// 由參數CusAccount(會員帳號)到PetRegistration表格中取得某個會員的所有寵物資料，
	@SuppressWarnings("unchecked")
	// 傳回值為一個PetBean的List物件；如果找不到對應的會員寵物資料，傳回值為null。
	@Override
	public List<PetBean> queryAllPets(String account) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PetBean p WHERE p.cusAccount = :account";
		List<PetBean> pets = session.createQuery(hql).setParameter("account", account).getResultList();
		return pets;
	}
	
	// 更新PetBean物件，更新資料庫PetRegistration表格中的寵物資料。
	@Override
	public int updatePet(PetBean pet) {
		int n = 0;
		Session session = factory.getCurrentSession();
		if (pet != null && pet.getPetID() != null) {
			session.saveOrUpdate(pet);
			n++;
		}
		return n;
	}

	
	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("PetDaoImpl_Hibernate類別不支援setConnection()方法");
	}

}
