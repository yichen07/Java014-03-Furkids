package _01_Member.Registration.service.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.dao.impl.MemberDaoImpl_Hibernate;
import _01_Member.Registration.dao.impl.PetDaoImpl_Hibernate;
import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;
import _01_Member.Registration.service.MemberService;
import _01_Member.util.HibernateUtils;

public class MemberServiceImpl implements MemberService {

	MemberDao mdao;
	PetDao pdao;
	SessionFactory factory;
	
	public MemberServiceImpl() {
		this.mdao = new MemberDaoImpl_Hibernate();
		this.pdao = new PetDaoImpl_Hibernate();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mdao.saveMember(mb);
			count++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return count;
	}

	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = mdao.accountExists(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return exist;
	}

	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = mdao.queryMember(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}

	@Override
	public MemberBean checkAccountPassword(String account, String password) {
//	public Object checkAccountPassword(String account, String password) {
		MemberBean mb = null;
//		Object mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = mdao.checkAccountPassword(account, password);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}
	
	
	@Override
	public int savePet(PetBean pet) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			pdao.savePet(pet);
			count++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return count;
	}

	@Override
	public PetBean queryPet(String account) {
		PetBean pet = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			pet = pdao.queryPet(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}
		return pet;
	}

}
