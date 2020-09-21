package _02_ShoppingSystem.ShoppingCart.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import _02_ShoppingSystem.ShoppingCart.dao.OrderListDao;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.ude.ProductStockException;

public class OrderListDaoImpl implements OrderListDao {
	Connection conn;

	public OrderListDaoImpl() {
		
	}
	
	@Override
	public double findItemAmount(OrderListBean oib) {
		double subtotal = oib.getOrdQuantity() * oib.getOrdUnitPrice();
		return subtotal;	
		}

	@Override
	public int updateProductStock(OrderListBean oib) {
		int n = 0;
		int stock = 0;
		String sql0 = "SELECT ComStock FROM Commodity WHERE ComID = ?";
		String sql1 = "UPDATE Commodity SET ComStock = ComStock - ? WHERE ComId = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql0);) {
			ps.setInt(1, oib.getComID());
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					stock = rs.getInt(1);
					if (stock < oib.getOrdQuantity()) {
						throw new ProductStockException("庫存數量不足: BookId: " 
								+ oib.getComID() + ", 在庫量: " + stock+ ", 訂購量: " + oib.getOrdQuantity());
					} else {
						;
					}
					try (PreparedStatement ps1 = conn.prepareStatement(sql1);) {
						ps1.setInt(1, oib.getOrdQuantity());
						ps1.setInt(2, oib.getComID());
						n = ps1.executeUpdate();
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderItemDaoImpl類別#updateProductStock()發生SQL例外: " + ex.getMessage());
		}
		return n;	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
