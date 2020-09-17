package _01_Member.Registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _01_Member.Registration.dao.PetDao;
import _01_Member.Registration.model.PetBean;

public class PetDaoImpl_Jdbc implements PetDao {

	private DataSource ds = null;
	private Connection con = null;
	
	public PetDaoImpl_Jdbc() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetDaoImpl_Jdbc類別#建構子發生例外: " + e.getMessage());
		}
	}

	// 儲存PetBean物件，將會員新增寵物資料pet新增到資料庫PetRegistration表格中。
	@Override
	public int savePet(PetBean pet) {
		String sql = "INSERT INTO PetRegistration "
				+ " (cusAccount, petName, petGender, petBirthday, petBreed, petVariety, petPhoto, petFileName) "
				+ " VALUES (?,?,?,?,?,?,?,?)";
		int n = 0;
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, pet.getCusAccount());
			ps.setString(2, pet.getPetName());
			ps.setString(3, pet.getPetGender());
			ps.setDate(4, pet.getPetBirthday());
			ps.setString(5, pet.getPetBreed());
			ps.setString(6, pet.getPetVariety());
			ps.setBlob(7, pet.getPetPhoto());
			ps.setString(8, pet.getPetFileName());
			
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetDaoImpl_Jdbc類別#savePet()發生例外: " 
					+ e.getMessage());
		}
		return n;
	}

	// 由參數BusAccount(商家帳號)到PetRegistration表格中取得某個會員的所有寵物資料，
	// 傳回值為一個PetBean的物件；如果找不到對應的會員寵物資料，傳回值為null。
	@Override
	public PetBean queryPet(String account) {
		PetBean pet = null;
		String sql = "SELECT * FROM PetRegistration WHERE cusAccount = ?";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
		){
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					pet = new PetBean();
					pet.setPetID(rs.getInt("petID"));
					pet.setCusAccount(rs.getString("cusAccount"));
					pet.setPetName(rs.getString("petName"));
					pet.setPetGender(rs.getString("petGender"));
					pet.setPetBirthday(rs.getDate("petBirthday"));
					pet.setPetBreed(rs.getString("petBreed"));
					pet.setPetVariety(rs.getString("petVariety"));
					pet.setPetPhoto(rs.getBlob("petPhoto"));
					pet.setPetFileName(rs.getString("petFileName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetDaoImpl_Jdbc類別#queryPet()發生例外: " 
					+ e.getMessage());
		}
		return pet;
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}

}
