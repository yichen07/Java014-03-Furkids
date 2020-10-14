package _04_ShoppingCart.model;

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
@Table(name="SaleOrder")
public class SaleOrderBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer s_OrderNo;		//訂單編號
	Integer s_M_No;			//會員編號
	Double	s_OrderSum;		//訂單金額
	String	s_M_Address; 	//出貨地址
	String  s_M_Name;		//會員姓名
	String  s_M_Phone;		//會員手機
	Integer s_Status;		//訂單狀態
	Date  s_OrderDate;		//下單日期
	Date  shippingDate;		//發貨日期
	@OneToMany(mappedBy="saleOrderBean", cascade=CascadeType.ALL)
	Set<SaleOrderItemsBean> items = new LinkedHashSet<>();
	
	public SaleOrderBean() {
		
	}

	public SaleOrderBean(Integer s_OrderNo, Integer s_M_No, Double s_OrderSum, String s_M_Address, String s_M_Name,
			String s_M_Phone, Integer s_Status, Date s_OrderDate, Date shippingDate, Set<SaleOrderItemsBean> items) {
		super();
		this.s_OrderNo = s_OrderNo;
		this.s_M_No = s_M_No;
		this.s_OrderSum = s_OrderSum;
		this.s_M_Address = s_M_Address;
		this.s_M_Name = s_M_Name;
		this.s_M_Phone = s_M_Phone;
		this.s_Status = s_Status;
		this.s_OrderDate = s_OrderDate;
		this.shippingDate = shippingDate;
		this.items = items;
	}

	public int getS_OrderNo() {
		return s_OrderNo;
	}

	public void setS_OrderNo(Integer s_OrderNo) {
		this.s_OrderNo = s_OrderNo;
	}

	public int getS_M_No() {
		return s_M_No;
	}

	public void setS_M_Id(Integer s_M_No) {
		this.s_M_No = s_M_No;
	}

	public Double getS_OrderSum() {
		return s_OrderSum;
	}

	public void setS_OrderSum(Double s_OrderSum) {
		this.s_OrderSum = s_OrderSum;
	}

	public String getS_M_Address() {
		return s_M_Address;
	}

	public void setS_M_Address(String s_M_Address) {
		this.s_M_Address = s_M_Address;
	}

	public String getS_M_Name() {
		return s_M_Name;
	}

	public void setS_M_Name(String s_M_Name) {
		this.s_M_Name = s_M_Name;
	}

	public String getS_M_Phone() {
		return s_M_Phone;
	}

	public void setS_M_Phone(String s_M_Phone) {
		this.s_M_Phone = s_M_Phone;
	}

	public int getS_Status() {
		return s_Status;
	}

	public void setS_Status(Integer s_Status) {
		this.s_Status = s_Status;
	}

	public Date getS_OrderDate() {
		return s_OrderDate;
	}

	public void setS_OrderDate(Date s_OrderDate) {
		this.s_OrderDate = s_OrderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Set<SaleOrderItemsBean> getItems() {
		return items;
	}

	public void setItems(Set<SaleOrderItemsBean> items) {
		this.items = items;
	}

	
}
