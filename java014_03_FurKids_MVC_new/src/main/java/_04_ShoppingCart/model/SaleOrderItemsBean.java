package _04_ShoppingCart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//本類別封裝單筆訂單資料
@Entity
@Table(name="SaleOrderItems")
public class SaleOrderItemsBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer soiNo;				//訂單明細編號
//	Integer soi_S_OrderNo;		//訂單編號
	Integer soi_P_Id;			//商品編號
	String soiDescription;		//商品敘述
	Integer soiQty;				//購買數量
	Double soiPrice;			//單價
	
	private String soiTitle;		//商品名稱
	private String soiCategory;		//商品類別
	@ManyToOne
	@JoinColumn(name = "soi_S_OrderNo")
	SaleOrderBean saleOrderBean;
	
//	public SaleOrderItemsBean(Integer soiNo, Integer soi_S_OrderNo, Integer soi_P_Id, String soiDescription,
	public SaleOrderItemsBean(Integer soiNo, Integer soi_P_Id, String soiDescription,
			Integer soiQty, Double soiPrice,String soiTitle) {
		super();
		this.soiNo = soiNo;							//訂單明細編號
//		this.soi_S_OrderNo = soi_S_OrderNo;			//訂單編號
		this.soi_P_Id = soi_P_Id;					//商品編號
		this.soiDescription = soiDescription;		//商品敘述
		this.soiQty = soiQty;						//購買數量
		this.soiPrice = soiPrice;					//單價
		this.soiTitle= soiTitle;					//商品名稱
	}
	
	public SaleOrderItemsBean(Integer soiNo, Integer soi_P_Id, String soiDescription, Integer soiQty, Double soiPrice,
			 String soiTitle, String soiCategory) {
		super();
		this.soiNo = soiNo;
		this.soi_P_Id = soi_P_Id;
		this.soiDescription = soiDescription;
		this.soiQty = soiQty;
		this.soiPrice = soiPrice;
		this.soiTitle = soiTitle;
		this.soiCategory = soiCategory;
	}
	
	public SaleOrderItemsBean() {
		super();
	}

	public Integer getSoiNo() {
		return soiNo;
	}
	public void setSoiNo(Integer soiNo) {
		this.soiNo = soiNo;
	}
//	public Integer getSoi_S_OrderNo() {
//		return soi_S_OrderNo;
//	}
//	public void setSoi_S_OrderNo(Integer soi_S_OrderNo) {
//		this.soi_S_OrderNo = soi_S_OrderNo;
//	}
	public Integer getSoi_P_Id() {
		return soi_P_Id;
	}
	public void setSoi_P_Id(Integer soi_P_Id) {
		this.soi_P_Id = soi_P_Id;
	}
	public String getSoiDescription() {
		return soiDescription;
	}
	public void setSoiDescription(String soiDescription) {
		this.soiDescription = soiDescription;
	}
	public Integer getSoiQty() {
		return soiQty;
	}
	public void setSoiQty(Integer soiQty) {
		this.soiQty = soiQty;
	}
	public Double getSoiPrice() {
		return soiPrice;
	}
	public void setSoiPrice(Double soiPrice) {
		this.soiPrice = soiPrice;
	}
	public String getSoiTitle() {
		return soiTitle;
	}
	public void setSoiTitle(String soiTitle) {
		this.soiTitle = soiTitle;
	}

	public String getSoiCategory() {
		return soiCategory;
	}

	public void setSoiCategory(String soiCategory) {
		this.soiCategory = soiCategory;
	}

	public SaleOrderBean getSaleOrderBean() {
		return saleOrderBean;
	}

	public void setSaleOrderBean(SaleOrderBean saleOrderBean) {
		this.saleOrderBean = saleOrderBean;
	}
	
}