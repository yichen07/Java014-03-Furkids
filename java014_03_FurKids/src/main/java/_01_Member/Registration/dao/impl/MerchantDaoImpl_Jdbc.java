package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.model.MerchantBean;

public class MerchantDaoImpl_Jdbc implements MerchantDao {

	private DataSource ds = null;
	private Connection con = null;
	
	public MerchantDaoImpl_Jdbc() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantDaoImpl_Jdbc類別#建構子發生例外: " + e.getMessage());
		}
	}

	// 儲存MerchantBean物件，將商家註冊資料mb新增到資料庫MerchantRegistration表格中。
	@Override
	public int saveMerchant(MerchantBean mb) {
		String sql = "INSERT INTO MerchantRegistration "
				+ " (busAccount, busPassword, busEmail) "
				+ " VALUES (?,?,?)";
		int n = 0;
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, mb.getBusAccount());
			ps.setString(2, mb.getBusPassword());
			ps.setString(3, mb.getBusEmail());
			
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantDaoImpl_Jdbc類別#saveMerchant()發生例外: " 
					+ e.getMessage());
		}
		return n;
	}

	// 判斷參數BusAccount(商家帳號)是否已經被現有商家使用，
	// 如果是，傳回true，表示此BusAccount(商家帳號)不能使用，
	// 否則傳回false，表示此BusAccount(商家帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		String sql = "SELECT * FROM MerchantRegistration WHERE busAccount = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantDaoImpl_Jdbc類別#accountExists()發生例外: " 
					+ e.getMessage());
		}
		return exist;
	}

	// 由參數BusAccount(商家帳號)到MerchantRegistration表格中取得某個會員的所有資料，
	// 傳回值為一個MerchantBean的物件；如果找不到對應的會員資料，傳回值為null。
	@Override
	public MerchantBean queryMerchant(String account) {
		MerchantBean mb = null;
		String sql = "SELECT * FROM MerchantRegistration WHERE busAccount = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MerchantBean();
					mb.setBusAccount(rs.getString("busAccount"));
					mb.setBusPassword(rs.getString("busPassword"));
					mb.setBusEmail(rs.getString("busEmail"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantDaoImpl_Jdbc類別#queryMerchant()發生例外: " 
					+ e.getMessage());
		}
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
					mb.setBusEmail(rs.getString("busEmail"));
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
