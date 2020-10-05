package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.model.MerchantBean;

@Repository
public class MerchantDaoImpl implements MerchantDao {
	@Autowired
	SessionFactory factory;
	private DataSource ds = null;
	private Connection con = null;
	
	public MerchantDaoImpl() {
		
	}

	// 儲存MerchantBean物件，將商家註冊資料mb新增到資料庫MerchantRegistration表格中。
	@Override
	public int saveMerchant(MerchantBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.merge(mb);
		return n;
	}

	// 判斷參數BusAccount(商家帳號)是否已經被現有會員或商家使用，
	// 如果是，傳回true，表示此BusAccount(商家帳號)不能使用，
	// 否則傳回false，表示此BusAccount(商家帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		session.merge(account);
		return exist;
	}

	// 由參數BusAccount(商家帳號)到MerchantRegistration表格中取得某個商家的所有資料，
	// 傳回值為一個MerchantBean的物件；如果找不到對應的商家資料，傳回值為null。
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
		Session session = factory.getCurrentSession();
		session.merge(account);
		return mb;
	}

	// 檢查使用者在登入時輸入的帳號密碼是否正確。
	// 如果正確傳回該帳號所對應的MerchantBean物件，否則傳回null。
	@Override
	public MerchantBean checkAccountPassword(String account, String password) {
		MerchantBean mb = null;
		String sql = "SELECT * FROM MerchantRegistration m WHERE m.busAccount = ? and m.busPassword = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MerchantBean();
					mb.setBusAccount(rs.getString("busAccount"));
					mb.setBusPassword(rs.getString("busPassword"));
					mb.setBusName(rs.getString("busName"));
					mb.setBusEmail(rs.getString("busEmail"));
					mb.setBusTel(rs.getString("busTel"));
					mb.setBusAddress(rs.getString("busAddress"));
					mb.setBusDescription(rs.getString("busDescription"));
					mb.setBusPhoto(rs.getBlob("busPhoto"));
					mb.setBusFileName(rs.getString("busFileName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantDaoImpl_Jdbc類別#queryMerchant()發生例外: " 
					+ e.getMessage());
		}
		return mb;
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

}
