package _02_ShoppingSystem.ShoppingCart.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_Init.util.DBService;
import _02_ShoppingSystem.ShoppingCart.dao.OrderDao;
import _02_ShoppingSystem.ShoppingCart.model.OrderBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;

public class OrderDaoImpl implements OrderDao {

	private String memberId = null;
	private Connection con;
	int orderNo = 0;
	
	public OrderDaoImpl() {
		
	}
	
	@Override
	public void insertOrder(OrderBean ob) {
        String sqlOrder = "Insert Into `order` "
        		+ " (CusAccount, OrdPrice, OrdAddress , OrdDateTime) "
        		+ " values(? , ? , ? , ?) ";
		
        String sqlItem = "Insert Into OrderList (OrdID, "
        		+ " ComID, ComName, OrdQuantity, OrdUnitPrice, "
        		+ " OrdValidity) "
        		+ " values(?, ?, ?, ?, ?, ?) ";
       
        ResultSet generatedKeys = null;
        
        try (
    			PreparedStatement ps = con.prepareStatement(sqlOrder, 
    				Statement.RETURN_GENERATED_KEYS);
    		) {
    			ps.setString(1, ob.getCusAccount());
    			ps.setDouble(2, ob.getOrdPrice());
    			ps.setString(3, ob.getOrdAddress());
    			Timestamp ts = new Timestamp(ob.getOrdDateTime().getTime());
    			ps.setTimestamp(4, ts);
    			ps.executeUpdate();
    			int id = 0;
    			// 取回剛才新增之訂單的主鍵值
    			generatedKeys = ps.getGeneratedKeys();
    			if (generatedKeys.next()) {
    				id = generatedKeys.getInt(1);
    			} else {
    				throw new RuntimeException("OrderDaoImpl類別#insertOrder()無法取得新增之orders表格的主鍵");
    				
    			}
    			Set<OrderListBean> items = ob.getItems();
    			try (PreparedStatement ps2 = con.prepareStatement(sqlItem);) {
    				for (OrderListBean oib : items) {
    					ps2.setInt(1, id);
    					ps2.setInt(2, oib.getComID());
    					ps2.setString(3, oib.getComName());
    					ps2.setInt(4, oib.getOrdQuantity());
    					ps2.setDouble(5, oib.getOrdUnitPrice());
    					ps2.setDate(6, oib.getOrdValidity());
    					ps2.executeUpdate();
    					ps2.clearParameters();
    				}
    			}
    		} catch (SQLException ex) {
    			ex.printStackTrace();
    			throw new RuntimeException("OrderDaoImpl類別#insertOrder()發生SQL例外: " + ex.getMessage());
    		}
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public OrderBean getOrder(int ordID) {
		OrderBean ob = null;
		DataSource ds = null;
		Set<OrderListBean> set = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}

		String sql = "SELECT * FROM Order WHERE OrdID = ? ";
		String sql1 = "SELECT * FROM OrderList WHERE OrdID = ? ";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
		) {
			ps.setInt(1, ordID);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					Integer ordId = rs.getInt("ordID");
					//String cancel = rs.getString("cancelTag");
					String cusAccount = rs.getString("cusAccount");
					Date ordDateTime = rs.getDate("ordDateTime");
					String ordAddress = rs.getString("ordAddress");
					double ordPrice = rs.getDouble("ordPrice");
					ob = new OrderBean(ordId, cusAccount, ordDateTime, ordPrice, ordAddress, null);
				}
			}
			ps1.setInt(1, ordID);
			try (
				ResultSet rs = ps1.executeQuery();
			) {
				set = new HashSet<>();
				while (rs.next()) {
					int ordDetailID = rs.getInt("ordDetailID");
					int orderNo2 = rs.getInt("ordID");
					int comID = rs.getInt("comID");
					Integer amount = rs.getInt("ordQuantity");
					Double uPrice = rs.getDouble("ordUnitPrice");
					OrderListBean oib = new OrderListBean(ordDetailID, orderNo2, comID, 
							 null,amount, uPrice, null, null);
					set.add(oib);
				}
				ob.setItems(set);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-2發生例外: " + ex.getMessage());
		}
		return ob;	}

	@Override
	public List<OrderBean> getAllOrders() {
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<OrderBean> list = new ArrayList<OrderBean>();
		String sql = "SELECT OrdID FROM Order";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				Integer no = rs.getInt(1);
				list.add(getOrder(no));
			}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;	}

	@Override
	public List<OrderBean> getMemberOrders(String memberId) {
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<OrderBean> list = new ArrayList<OrderBean>();
		String sql = "SELECT OrdID FROM Order where CusAccount = ? Order by orderDateTime desc ";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, memberId);
				try (
					ResultSet rs = ps.executeQuery();
				) {
					while (rs.next()) {
						Integer no = rs.getInt(1);
						list.add(getOrder(no));
					}
				}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;	
		}

}
