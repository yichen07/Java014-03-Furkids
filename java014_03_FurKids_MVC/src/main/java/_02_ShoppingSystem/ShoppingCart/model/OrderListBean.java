package _02_ShoppingSystem.ShoppingCart.model;

import java.sql.Date;

public class OrderListBean {

	Integer ordDetailID;
	Integer ordID;
	Integer comID;
	String  comName;
	Integer ordQuantity;
	Double  ordUnitPrice;
	Date    ordValidity;
	
	private String busName;

	public OrderListBean(Integer ordDetailID, Integer ordID, Integer comID, String comName, Integer ordQuantity,
			Double ordUnitPrice, Date ordValidity, String busName) {
		this.ordDetailID = ordDetailID;
		this.ordID = ordID;
		this.comID = comID;
		this.comName = comName;
		this.ordQuantity = ordQuantity;
		this.ordUnitPrice = ordUnitPrice;
		this.ordValidity = ordValidity;
		this.busName = busName;
	}

	public OrderListBean(Integer ordID, Integer comID, String comName, Integer ordQuantity, Double ordUnitPrice,
			Date ordValidity) {
		this.ordID = ordID;
		this.comID = comID;
		this.comName = comName;
		this.ordQuantity = ordQuantity;
		this.ordUnitPrice = ordUnitPrice;
		this.ordValidity = ordValidity;
	}

	public OrderListBean() {
		
	}

	public Integer getOrdDetailID() {
		return ordDetailID;
	}

	public void setOrdDetailID(Integer ordDetailID) {
		this.ordDetailID = ordDetailID;
	}

	public Integer getOrdID() {
		return ordID;
	}

	public void setOrdID(Integer ordID) {
		this.ordID = ordID;
	}

	public Integer getComID() {
		return comID;
	}

	public void setComID(Integer comID) {
		this.comID = comID;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer getOrdQuantity() {
		return ordQuantity;
	}

	public void setOrdQuantity(Integer ordQuantity) {
		this.ordQuantity = ordQuantity;
	}

	public Double getOrdUnitPrice() {
		return ordUnitPrice;
	}

	public void setOrdUnitPrice(Double ordUnitPrice) {
		this.ordUnitPrice = ordUnitPrice;
	}

	public Date getOrdValidity() {
		return ordValidity;
	}

	public void setOrdValidity(Date ordValidity) {
		this.ordValidity = ordValidity;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}
	
	
	
}
