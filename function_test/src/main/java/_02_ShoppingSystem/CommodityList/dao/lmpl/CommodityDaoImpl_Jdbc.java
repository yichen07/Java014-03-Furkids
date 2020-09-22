package _02_ShoppingSystem.CommodityList.dao.lmpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _00_Init.util.GlobalService;
import _02_ShoppingSystem.CommodityList.dao.CommodityDao;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;

public class CommodityDaoImpl_Jdbc implements Serializable, CommodityDao {
	
	private static final long serialVersionUID = 1L;

	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;
	DataSource ds = null;
	
	String selected = "";

	public CommodityDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CommodityDaoImpl_Jdbc()#建構子發生例外: " 
										+ ex.getMessage());
		}
		
	}

	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
        totalPages = (int)(Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}
	
	// 查詢某一頁商品資料
	@Override
	public Map<Integer, CommodityBean> getPageCommodity(int pageNo) {
 		Map<Integer, CommodityBean> map = new HashMap<>();
		
		String sql0 = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY COMID)"
				    + " AS RowNum, c.Comid, c.BusAccount,c.BusName,c.ComName, c.ComStock, " 
				    + " c.ComSort, c.ComDescription, c.ComValidity, c.ComImage, c.ComPrice "
				    + " FROM Commoditylist c ) "
				    + " AS NewTable WHERE RowNum >= ? AND RowNum <= ?";
		
		String sql = sql0;
		
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;
		
		try (
				Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql);
			) {
				ps.setInt(1, startRecordNo);
				ps.setInt(2, endRecordNo);
				try (
					ResultSet rs = ps.executeQuery();
				) {
					// 只要還有紀錄未取出，rs.next()會傳回true
					// 迴圈內將逐筆取出ResultSet內的紀錄
					while (rs.next()) {
						// 準備一個新的BookBean，將ResultSet內的一筆紀錄移植到BookBean內
						CommodityBean bean = new CommodityBean();    	
						bean.setComId(rs.getInt("ComId"));		
						bean.setBusAccount(rs.getString("BusAccount"));
						bean.setBusName(rs.getString("BusName"));
						bean.setComName(rs.getString("ComName"));
						bean.setComStock(rs.getInt("ComStock"));
						bean.setComSort(rs.getString("ComSort"));
						bean.setComDescription(rs.getString("ComDescription"));
						bean.setComValidity(rs.getDate("ComValidity"));
						bean.setComImage(rs.getBlob("ComImage"));
						bean.setComPrice(rs.getDouble("ComPrice"));
						// 最後將BookBean物件放入大的容器內
						map.put(rs.getInt("ComId"), bean);
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("CommodityDaoImpl_Jdbc()#getPageCommodity()發生例外: " 
											+ ex.getMessage());
			}
		
		
		return map;
	}
	
	@Override
	public long getRecordCounts() {
		long count = 0;
		String sql = "SELECT count(*) FROM commoditylist";
		try (
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					count = rs.getLong(1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("CommodityDaoImpl_Jdbc()#getRecordCounts()發生例外: " 
											+ ex.getMessage());
			}
			return count;
	}
	
	public List<String> getComSort() {
		String sql = "SELECT DISTINCT ComSort FROM CommodityList";
		List<String> list = new ArrayList<>();
		try (
			Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				String cate = rs.getString(1);
				if (cate != null) {
					list.add(cate);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("CommodityDaoImpl_Jdbc()#getCommodity()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}


	
	@Override
	public String getComSortTag() {
		String ans = "";
		List<String> list = getComSort();
		ans += "<SELECT name='Comsort'>";
		for (String sort : list) {
			if (sort.equals(selected)) {
				ans += "<option value='" + sort + "' selected>" + sort + "</option>";
			} else {
				ans += "<option value='" + sort + "'>" + sort + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;	}



	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}



	@Override
	public int saveCommodity(CommodityBean bean) {
		int n = 0;
		String sql = "INSERT INTO Commodity " 
				+ " (ComID, BusAccount, BusName, ComName, "
				+ " ComStock, ComSort, ComDescription, ComValidity, ComImage, ComPrice) " 
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (
			Connection connection = ds.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);
		) {
			pStmt.setInt(1, bean.getComId());
			pStmt.setString(2, bean.getBusAccount());
			pStmt.setString(3, bean.getBusName());
			pStmt.setString(3, bean.getComName());
			pStmt.setInt(5, bean.getComStock());
			pStmt.setString(6, bean.getComSort());
			pStmt.setString(7, bean.getComDescription());
			pStmt.setDate(8, bean.getComValidity());
			pStmt.setBlob(9, bean.getComImage());
			pStmt.setDouble(10, bean.getComPrice());
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#saveBook()發生例外: " 
										+ ex.getMessage());
		}
		return n;	
	}


	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage =  recordsPerPage;	
	}
	
	

	@Override
	public CommodityBean getCommodity(int ComId) {
		CommodityBean bean = null;
		String sql = "SELECT * FROM Commoditylist WHERE ComId = ?";
		try (
				Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql);
			) {
				ps.setInt(1, ComId);
				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						bean = new CommodityBean();
						bean.setComId(rs.getInt("ComId"));		
						bean.setBusAccount(rs.getString(2));
						bean.setBusName(rs.getString(3));
						bean.setComName(rs.getString(4));
						bean.setComStock(rs.getInt(5));
						bean.setComSort(rs.getString("ComSort"));
						bean.setComDescription(rs.getString(7));
						bean.setComValidity(rs.getDate(8));
						bean.setComImage(rs.getBlob(9));
						bean.setComPrice(rs.getDouble(10));
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("CommodityDaoImpl_Jdbc()#queryBook()發生例外: " 
											+ ex.getMessage());
			}
		
		return bean;
	}

	@Override
	public void setSelected(String selected) {
		this.selected = selected;		
	}


	@Override
	public int updateCommodity(CommodityBean bean, long sizeInBytes) {
		int n = 0;
//		String sql = "UPDATE Commodity SET " 
//				+ " ComName=?,  author=?,  listPrice=?, discount = ?, coverImage = ?, "
//				+ " fileName=?, bookNo=?, stock=?, companyId=? , category = ? WHERE bookId = ?";
//		if (sizeInBytes == -1) { // 不修改圖片
//			n = updateCommodity(bean);
//			return n;
//		}
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.setString(1, bean.getTitle());
//			ps.setString(2, bean.getAuthor());
//			ps.setDouble(3, bean.getListPrice());
//			ps.setDouble(4, bean.getDiscount());
//			ps.setBlob(5, bean.getCoverImage());
//			ps.setString(6, bean.getFileName());
//			ps.setString(7, bean.getBookNo());
//			ps.setInt(8, bean.getStock());
//			ps.setInt(9, bean.getCompanyId());
//			ps.setString(10, bean.getCategory());
//			ps.setInt(11, bean.getBookId());
//			n = ps.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("CommodityDaoImpl_Jdbc()#updateBook(BookBean, long)發生例外: " 
//										+ ex.getMessage());
//		}
		return n;
	}
//	
//
	public int updateCommodity(CommodityBean bean) {
		int n = 0;
//		String sql = "UPDATE Commodity SET " 
//				+ " ComName=?,  author=?,  listPrice=?, discount = ?, coverImage = ?, "
//				+ " fileName=?, bookNo=?, stock=?, companyId=? , category = ? WHERE bookId = ?";
//		
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.setString(1, bean.getTitle());
//			ps.setString(2, bean.getAuthor());
//			ps.setDouble(3, bean.getListPrice());
//			ps.setDouble(4, bean.getDiscount());
//			ps.setBlob(5, bean.getCoverImage());
//			ps.setString(6, bean.getFileName());
//			ps.setString(7, bean.getBookNo());
//			ps.setInt(8, bean.getStock());
//			ps.setInt(9, bean.getCompanyId());
//			ps.setString(10, bean.getCategory());
//			ps.setInt(11, bean.getBookId());
//			n = ps.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("CommodityDaoImpl_Jdbc()#updateBook(BookBean, long)發生例外: " 
//										+ ex.getMessage());
//		}
		return n;
	}






	
}
