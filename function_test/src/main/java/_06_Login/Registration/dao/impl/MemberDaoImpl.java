package _06_Login.Registration.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _06_Login.Registration.dao.MemberDao;
import _06_Login.Registration.model.MemberBean;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
	private Connection con = null;
	
	public MemberDaoImpl() {

	}
	
	// 儲存MemberBean物件，將會員註冊資料mb新增到資料庫MembraneRegistration表格中。
	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.merge(mb);
		return n;
	}

	
	// 判斷參數CusAccount(會員帳號)是否已經被現有會員或商家使用，
	// 如果是，傳回true，表示此CusAccount(會員帳號)不能使用，
	// 否則傳回false，表示此CusAccount(會員帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		session.merge(account);
		return exist;
	}

	
	// 由參數CusAccount(會員帳號)到MembraneRegistration表格中取得某個會員的所有資料，
	// 傳回值為一個MemberBean的物件；如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		session.merge(account);
		return mb;
	}

	
	// 檢查使用者在登入時輸入的帳號密碼是否正確。
	// 如果正確傳回該帳號所對應的MemberBean物件，否則傳回null。
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> checkAccountPassword(String account, String password) {
		Session session = factory.getCurrentSession();
		String sql = "SELECT * FROM MembraneRegistration m WHERE m.cusAccount = ? and m.cusPassword = ?";
		List<MemberBean> list = new ArrayList<>();
		list = session.createQuery(sql).setParameter("?", account).getResultList();
		return list;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	

}
