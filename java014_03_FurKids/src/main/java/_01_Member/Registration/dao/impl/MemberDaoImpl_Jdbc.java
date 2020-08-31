package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _01_Member.Registration.dao.MemberDao;
import _01_Member.Registration.model.MemberBean;

public class MemberDaoImpl_Jdbc implements MemberDao {
	
	private DataSource ds = null;
	private Connection con = null;
	
	public MemberDaoImpl_Jdbc() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + e.getMessage());
		}
	}
	
	// 儲存MemberBean物件，將會員註冊資料mb新增到資料庫MembraneRegistration表格中。
	@Override
	public int saveMember(MemberBean mb) {
		String sql = "INSERT INTO MembraneRegistration "
				+ " (cusAccount, cusPassword, cusName, cusGender, cusBirthday, "
				+ " cusEmail, cusTel, cusAddress, cusPhoto, cusFileName) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
		int n = 0;
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, mb.getCusAccount());
			ps.setString(2, mb.getCusPassword());
			ps.setString(3, mb.getCusName());
			ps.setString(4, mb.getCusGender());
			ps.setDate(5, mb.getCusBirthday());
			ps.setString(6, mb.getCusEmail());
			ps.setString(7, mb.getCusTel());
			ps.setString(8, mb.getCusAddress());
			ps.setBlob(9, mb.getCusPhoto());
			ps.setString(10, mb.getCusFileName());
			
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
					+ e.getMessage());
		}
		return n;
	}

	
	// 判斷參數CusAccount(會員帳號)是否已經被現有會員使用，
	// 如果是，傳回true，表示此CusAccount(會員帳號)不能使用，
	// 否則傳回false，表示此CusAccount(會員帳號)可使用。
	@Override
	public boolean accountExists(String account) {
		boolean exist = false;
		String sql = "SELECT * FROM MembraneRegistration WHERE cusAccount = ?";
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
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#accountExists()發生例外: " 
					+ e.getMessage());
		}
		return exist;
	}

	
	// 由參數CusAccount(會員帳號)到MembraneRegistration表格中取得某個會員的所有資料，
	// 傳回值為一個MemberBean的物件；如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String account) {
		MemberBean mb = null;
		String sql = "SELECT * FROM MembraneRegistration WHERE cusAccount = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setCusAccount(rs.getString("cusAccount"));
					mb.setCusPassword(rs.getString("cusPassword"));
					mb.setCusName(rs.getString("cusName"));
					mb.setCusGender(rs.getString("cusGender"));
					mb.setCusBirthday(rs.getDate("cusBirthday"));
					mb.setCusEmail(rs.getString("cusEmail"));
					mb.setCusTel(rs.getString("cusTel"));
					mb.setCusAddress(rs.getString("cusAddress"));
					mb.setCusPhoto(rs.getBlob("cusPhoto"));
					mb.setCusFileName(rs.getString("cusFileName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " 
					+ e.getMessage());
		}
		return mb;
	}

	
	// 檢查使用者在登入時輸入的帳號密碼是否正確。
	// 如果正確傳回該帳號所對應的MemberBean物件，否則傳回null。
	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		MemberBean mb = null;
		String sql = "SELECT * FROM MembraneRegistration m WHERE m.cusAccount = ? and m.cusPassword = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setCusAccount(rs.getString("cusAccount"));
					mb.setCusPassword(rs.getString("cusPassword"));
					mb.setCusName(rs.getString("cusName"));
					mb.setCusGender(rs.getString("cusGender"));
					mb.setCusBirthday(rs.getDate("cusBirthday"));
					mb.setCusEmail(rs.getString("cusEmail"));
					mb.setCusTel(rs.getString("cusTel"));
					mb.setCusAddress(rs.getString("cusAddress"));
					mb.setCusPhoto(rs.getBlob("cusPhoto"));
					mb.setCusFileName(rs.getString("cusFileName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " 
					+ e.getMessage());
		}
		return mb;
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	

}
