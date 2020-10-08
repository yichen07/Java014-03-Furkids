package _02_ShoppingSystem.ShoppingCart.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
// 本類別存放訂單資料
@Entity
@Table(name="`Order`")
public class OrderBean { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer ordId;
    String cusAccount;
    Date ordDateTime;
	Double ordPrice;
	String ordAddress;				
	@OneToMany(mappedBy="orderBean", cascade=CascadeType.ALL)
	Set<OrderListBean> items = new LinkedHashSet<>();

    
    


	public OrderBean(Integer ordId, String cusAccount, Date ordDateTime, Double ordPrice, String ordAddress,
			Set<OrderListBean> items) {
		super();
		this.ordId = ordId;
		this.cusAccount = cusAccount;
		this.ordDateTime = ordDateTime;
		this.ordPrice = ordPrice;
		this.ordAddress = ordAddress;
		this.items = items;
	}



	public Integer getOrdId() {
		return ordId;
	}


	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}


	public String getCusAccount() {
		return cusAccount;
	}


	public void setCusAccount(String cusAccount) {
		this.cusAccount = cusAccount;
	}


	public Date getOrdDateTime() {
		return ordDateTime;
	}


	public void setOrdDateTime(Date ordDateTime) {
		this.ordDateTime = ordDateTime;
	}


	public Double getOrdPrice() {
		return ordPrice;
	}


	public void setOrdPrice(Double ordPrice) {
		this.ordPrice = ordPrice;
	}
	
	
	public String getOrdAddress() {
		return ordAddress;
	}


	public void setOrdAddress(String ordAddress) {
		this.ordAddress = ordAddress;
	}


	public Set<OrderListBean> getItems() {
		return items;
	}


	public void setItems(Set<OrderListBean> items) {
		this.items = items;
	}
	
	
}
