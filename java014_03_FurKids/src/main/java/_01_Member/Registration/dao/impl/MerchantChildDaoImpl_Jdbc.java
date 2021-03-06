package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _01_Member.Registration.dao.MerchantChildDao;
import _01_Member.Registration.model.MerchantChildBean;

public class MerchantChildDaoImpl_Jdbc implements MerchantChildDao {

	private DataSource ds = null;
	private Connection con = null;
	
	public MerchantChildDaoImpl_Jdbc() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantChildDaoImpl_Jdbc類別#建構子發生例外: " + e.getMessage());
		}
	}

	// 儲存MerchantChildBean物件，將商家分店註冊資料mb新增到資料庫MerchantChildRegistration表格中。
	@Override
	public int saveMerchantChild(MerchantChildBean mb) {
		String sql = "INSERT INTO MerchantChildRegistration "
				+ " (busAccount, busChildName, busChildTel, busChildAddress, busChildDescription, busChildPhoto, busChildFileName) "
				+ " VALUES (?,?,?,?,?,?,?)";
		int n = 0;
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, mb.getBusAccount());
			ps.setString(2, mb.getBusChildName());
			ps.setString(3, mb.getBusChildTel());
			ps.setString(4, mb.getBusChildAddress());
			ps.setString(5, mb.getBusChildDescription());
			ps.setBlob(6, mb.getBusChildPhoto());
			ps.setString(7, mb.getBusChildFileName());
			
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantChildDaoImpl_Jdbc類別#saveMerchantChild()發生例外: " 
					+ e.getMessage());
		}
		return n;
	}

	// 由參數BusAccount(商家帳號)到MerchantChildRegistration表格中取得某個商家的所有資料，
	// 傳回值為一個MerchantChildBean的物件；如果找不到對應的商家資料，傳回值為null。
	@Override
	public MerchantChildBean queryMerchantChild(String account) {
		MerchantChildBean mb = null;
		String sql = "SELECT * FROM MerchantChildRegistration WHERE busAccount = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MerchantChildBean();
					mb.setBusAccount(rs.getString("busAccount"));
					mb.setBusChildNo(rs.getInt("busChildNo"));
					mb.setBusChildName(rs.getString("busChildName"));
					mb.setBusChildTel(rs.getString("busChildTel"));
					mb.setBusChildAddress(rs.getString("busChildAddress"));
					mb.setBusChildDescription(rs.getString("busChildDescription"));
					mb.setBusChildPhoto(rs.getBlob("busChildPhoto"));
					mb.setBusChildFileName(rs.getString("busChildFileName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MerchantChildDaoImpl_Jdbc類別#queryMerchantChild()發生例外: " 
					+ e.getMessage());
		}
		return mb;
	}

	// 判斷參數CusAccount(會員帳號)是否已經被現有會員或商家使用，
		// 如果是，傳回true，表示此CusAccount(會員帳號)不能使用，
		// 否則傳回false，表示此CusAccount(會員帳號)可使用。
		@Override
		public boolean merchantChildExists(String account, String address) {
			boolean exist = false;
			String sql = "SELECT * FROM MerchantChildRegistration WHERE busAccount = ? AND busChildAddress = ? ";
			try (
					Connection con = ds.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, account);
				ps.setString(2, address);
				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						exist = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberChildDaoImpl_Jdbc類別#merchantChildExists()發生例外: " 
						+ e.getMessage());
			}
			return exist;
		}
	
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}

}
