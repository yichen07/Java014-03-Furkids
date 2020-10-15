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
// �����O�s��q����
@Entity
@Table(name="`Order`")
public class OrderBean { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer ordId;
    String cusAccount;
    Date ordDateTime;
	Double ordPrice;
	String ordBuyName;
	Integer ordBuyPhone;
	String ordBuyEmail;
	String ordReciveName;
	Integer ordRecivePhone;
	String ordAddress;				
	@OneToMany(mappedBy="orderBean", cascade=CascadeType.ALL)
	Set<OrderListBean> items = new LinkedHashSet<>();

 
	


	public OrderBean(Integer ordId, String cusAccount, Date ordDateTime, Double ordPrice, String ordBuyName,
			Integer ordBuyPhone, String ordBuyEmail, String ordReciveName, Integer ordRecivePhone, String ordAddress,
			Set<OrderListBean> items) {
		super();
		this.ordId = ordId;
		this.cusAccount = cusAccount;
		this.ordDateTime = ordDateTime;
		this.ordPrice = ordPrice;
		this.ordBuyName = ordBuyName;
		this.ordBuyPhone = ordBuyPhone;
		this.ordBuyEmail = ordBuyEmail;
		this.ordReciveName = ordReciveName;
		this.ordRecivePhone = ordRecivePhone;
		this.ordAddress = ordAddress;
		this.items = items;
	}




	public String getOrdBuyName() {
		return ordBuyName;
	}




	public void setOrdBuyName(String ordBuyName) {
		this.ordBuyName = ordBuyName;
	}




	public Integer getOrdBuyPhone() {
		return ordBuyPhone;
	}




	public void setOrdBuyPhone(Integer ordBuyPhone) {
		this.ordBuyPhone = ordBuyPhone;
	}




	public String getOrdBuyEmail() {
		return ordBuyEmail;
	}




	public void setOrdBuyEmail(String ordBuyEmail) {
		this.ordBuyEmail = ordBuyEmail;
	}




	public String getOrdReciveName() {
		return ordReciveName;
	}




	public void setOrdReciveName(String ordReciveName) {
		this.ordReciveName = ordReciveName;
	}




	public Integer getOrdRecivePhone() {
		return ordRecivePhone;
	}




	public void setOrdRecivePhone(Integer ordRecivePhone) {
		this.ordRecivePhone = ordRecivePhone;
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
