package _02_ShoppingSystem.ShoppingCart.model;
					
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="OrderList")
public class OrderListBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer ordDetailID;
//	Integer ordID;
	Integer comID;
	String  comName;
	Integer ordQuantity;
	Double  ordUnitPrice;
	Date    ordValidity;
	
	private String busName;
	@ManyToOne
	@JoinColumn(name = "ordID")
	OrderBean orderBean;

	public OrderListBean(Integer ordDetailID, Integer comID, String comName, Integer ordQuantity,
			Double ordUnitPrice, Date ordValidity, String busName) {
		this.ordDetailID = ordDetailID;
		//this.ordID = ordID;
		this.comID = comID;
		this.comName = comName;
		this.ordQuantity = ordQuantity;
		this.ordUnitPrice = ordUnitPrice;
		this.ordValidity = ordValidity;
		this.busName = busName;
	}

	public OrderListBean(Integer comID, String comName, Integer ordQuantity, Double ordUnitPrice,
			Date ordValidity) {
//		this.ordID = ordID;
		this.comID = comID;
		this.comName = comName;
		this.ordQuantity = ordQuantity;
		this.ordUnitPrice = ordUnitPrice;
		this.ordValidity = ordValidity;
	}



	public Integer getOrdDetailID() {
		return ordDetailID;
	}

	public void setOrdDetailID(Integer ordDetailID) {
		this.ordDetailID = ordDetailID;
	}

//	public Integer getOrdID() {
//		return ordID;
//	}

//	public void setOrdID(Integer ordID) {
//		this.ordID = ordID;
//	}

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

	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}	
	
	
}
