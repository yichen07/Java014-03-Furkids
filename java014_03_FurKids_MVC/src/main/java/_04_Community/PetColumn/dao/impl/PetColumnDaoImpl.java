package _04_Community.PetColumn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _04_Community.PetColumn.dao.PetColumnDao;
import _04_Community.PetColumn.model.PetColumnBean;

public class PetColumnDaoImpl implements PetColumnDao {

	private DataSource ds = null;

	public PetColumnDaoImpl() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別#建構子發生例外: " + e.getMessage());
		}
	}

	@Override
	public int saveBlogIndex(PetColumnBean pcb) {
		String sql = "INSERT INTO PetColumn (PCTitle, PCImage, PCContent, PCFounder, PCAccount) VALUES (?,?,?,?,?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, pcb.getPCTitle());
			ps.setBlob(2, pcb.getPCImage());
			ps.setString(3, pcb.getPCContent());
			ps.setString(4, pcb.getPCFounder());
			ps.setString(5, pcb.getPCAccount());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別savePetColumn()發生例外: " + e.getMessage());
		}
		return n;
	}

	@Override
	public List<PetColumnBean> selectBlogIndexAll() {
		String sql = "select * from petcolumn where PCTitle is not null and PCTitle <>''";
		List<PetColumnBean> pcbList = new ArrayList<>();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					PetColumnBean pcb = new PetColumnBean();
					pcb.setPCID(rs.getString("PCID"));
					pcb.setPCFID(rs.getString("PCFID"));
					pcb.setPCTitle(rs.getString("PCTitle"));
					pcb.setPCContent(rs.getString("PCContent"));
					pcb.setPCImage(rs.getBlob("PCImage"));
					pcb.setPCFounder(rs.getString("PCFounder"));
					pcb.setPCAccount(rs.getString("PCAccount"));
					pcb.setPCViews(rs.getString("PCViews"));
					pcb.setPCDateTime(rs.getString("PCDateTime"));
					pcbList.add(pcb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogIndexAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public PetColumnBean getPetColumn(String pcid) {
		String sql = "select * from PetColumn where PCID=?";
		PetColumnBean pcb = new PetColumnBean();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, pcid);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					pcb.setPCID(rs.getString("PCID"));
					pcb.setPCFID(rs.getString("PCFID"));
					pcb.setPCTitle(rs.getString("PCTitle"));
					pcb.setPCContent(rs.getString("PCContent"));
					pcb.setPCImage(rs.getBlob("PCImage"));
					pcb.setPCFounder(rs.getString("PCFounder"));
					pcb.setPCAccount(rs.getString("PCAccount"));
					pcb.setPCViews(rs.getString("PCViews"));
					pcb.setPCDateTime(rs.getString("PCDateTime"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別getPetColumn()發生例外: " + e.getMessage());
		}
		return pcb;
	}

	@Override
	public List<PetColumnBean> selectBlogArticalAll(String pcfid) {
		String sql = "select * from petcolumn where PCFID=? and PCFID is not null and PCFID <>''";
		List<PetColumnBean> pcbList = new ArrayList<>();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, pcfid);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					PetColumnBean pcb = new PetColumnBean();
					pcb.setPCID(rs.getString("PCID"));
					pcb.setPCFID(rs.getString("PCFID"));
					pcb.setPCTitle(rs.getString("PCTitle"));
					pcb.setPCContent(rs.getString("PCContent"));
					pcb.setPCImage(rs.getBlob("PCImage"));
					pcb.setPCFounder(rs.getString("PCFounder"));
					pcb.setPCAccount(rs.getString("PCAccount"));
					pcb.setPCViews(rs.getString("PCViews"));
					pcb.setPCDateTime(rs.getString("PCDateTime"));
					pcbList.add(pcb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogArticalAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public int saveBlogArtical(PetColumnBean pcb) {
		String sql = "INSERT INTO PetColumn (PCFID, PCContent, PCFounder, PCAccount) VALUES (?,?,?,?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, pcb.getPCFID());
			ps.setString(2, pcb.getPCContent());
			ps.setString(3, pcb.getPCFounder());
			ps.setString(4, pcb.getPCAccount());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別savePetColumn()發生例外: " + e.getMessage());
		}
		return n;
	}

	@Override
	public int getpcfidCOUNT(String pcfid) {
		String sql = "select COUNT(*) from petcolumn where PCFID=?";
		int count = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, pcfid);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別getpcfidCOUNT()發生例外: " + e.getMessage());
		}
		return count;
	}

	@Override
	public int updatePCViews(String pcid, String PCViews) {
		String sql = "UPDATE petcolumn SET PCViews=? where PCID=?";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, Integer.parseInt(PCViews) + 1);
			ps.setString(2, pcid);
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別updatePCViews()發生例外: " + e.getMessage());
		}
		return n;
	}

}
