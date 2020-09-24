package _01_Member.Registration.dao.impl;

import java.sql.Connection;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.model.PetBean;
import _01_Member.util.HibernateUtils;

public class PetDaoImpl_Hibernate implements PetDao {
	
	SessionFactory factory;
	
	public PetDaoImpl_Hibernate() {
		this.factory = HibernateUtils.getSessionFactory();
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

	// 由參數BusAccount(商家帳號)到PetRegistration表格中取得某個會員的所有寵物資料，
	// 傳回值為一個PetBean的物件；如果找不到對應的會員寵物資料，傳回值為null。
	@Override
	public PetBean queryPet(String account) {
		PetBean pet = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM PetBean p WHERE p.cusAccount = :account";
		try {
			pet = (PetBean) session.createQuery(hql).setParameter("account", account).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			pet = null;
		}
		return pet;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("PetDaoImpl_Hibernate類別不支援setConnection()方法");
	}

}
