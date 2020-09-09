package _03_FriendlyService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _03_FriendlyService.model.ConvenienceBean;



public class ConvenienceDaoImp_JDBC {
	
	private DataSource ds = null;
	private Connection conn = null;
	
	public ConvenienceDaoImp_JDBC() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("ConvenienceDaoImp_JDBC#建構子發生例外: " + ex.getMessage());
		}
	}
	
	
//	public ConvenienceBean checkIdConvenience(String userId) {
//		ConvenienceBean cb = null;
//		String sql = "SELECT * FROM Convenience WHERE BusAccount = ?";
//		try (
//			Connection con = ds.getConnection(); 
//			PreparedStatement ps = con.prepareStatement(sql);
//		) {
//			ps.setString(1, userId);
//			try (ResultSet rs = ps.executeQuery();) {
//				if (rs.next()) {
//					cb = new ConvenienceBean();
//					cb.setBusAccount(rs.getString("BusAccount"));
//					cb.setBusChildNo(rs.getInt("BusChildNo"));
//					cb.setConItem(rs.getString("ConItem"));
//					cb.setConItemList(rs.getString("conItemList"));
//					cb.setConDateTime(rs.getString("conDateTime"));
//				}
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("ConvenienceDaoImp_JDBC類別#checkIdConvenience()發生SQL例外: " 
//					+ ex.getMessage());
//		}
//		return cb;
//	}
	
	//以帳號去資料庫抓該帳號有預約上架服務的N筆資料
	public Map<Integer, ConvenienceBean> checkIdConvenience(String userId) {
		Map<Integer, ConvenienceBean> map = new HashMap<>();
//		String sql = "SELECT * FROM Convenience WHERE BusAccount = ?";
		String sql = "SELECT a.BusAccount, a.BusChildNo, a.ConItem, a.ConItemList, a.ConDateTime,"
				+ " b.BusName, b.BusTel, b.BusAddress, b.BusDescription, c.BusEmail"
				+ "  FROM Convenience a LEFT JOIN MerchantChildRegistration b"
				+ " ON a.BusChildNo = b.BusChildNo LEFT JOIN MerchantRegistration c"
				+ " ON a.BusAccount = c.BusAccount WHERE a.BusAccount = ?";
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ConvenienceBean bean = new ConvenienceBean();
					bean = new ConvenienceBean();
					bean.setBusAccount(rs.getString("BusAccount"));
					bean.setBusChildNo(rs.getInt("BusChildNo"));
					bean.setConItem(rs.getString("ConItem"));
					bean.setConItemList(rs.getString("conItemList"));
					bean.setConDateTime(rs.getString("conDateTime"));
					bean.setBusName(rs.getString("BusName"));
					bean.setBusTel(rs.getInt("BusTel"));
					bean.setBusAddress(rs.getString("BusAddress"));
					bean.setBusDescription(rs.getString("BusDescription"));
					bean.setBusEmail(rs.getString("BusEmail"));
					map.put(rs.getInt("BusChildNo"), bean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ConvenienceDaoImp_JDBC類別#checkIdConvenience()發生SQL例外: " 
					+ ex.getMessage());
		}
		return map;
	}
	

}
