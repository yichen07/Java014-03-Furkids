package _02_ShoppingSystem.CommodityList.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class CommodityBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ComId;
	private String BusAccount;
	private String BusName;
	private String ComName;   //商品名稱
	private Integer ComStock;
	private String ComSort;
	private String ComDescription;
	private Date ComValidity;  //有效期限
	private Blob ComImage; //商品圖片
	private Double ComPrice;  //單價
	
	
	public CommodityBean(Integer comId, String busAccount, String busName, String comName, Integer comStock,
			String comSort, String comDescription, Date comValidity, Blob comImage, Double comPrice) {
		this.ComId = comId;
		this.BusAccount = busAccount;
		this.BusName = busName;
		this.ComName = comName;
		this.ComStock = comStock;
		this.ComSort = comSort;
		this.ComDescription = comDescription;
		this.ComValidity = comValidity;
		this.ComImage = comImage;
		this.ComPrice = comPrice;
	}

	public CommodityBean() {
	}
	
	public Integer getComId() {
		return ComId;
	}


	public void setComId(Integer comId) {
		this.ComId = comId;
	}


	public String getBusAccount() {
		return BusAccount;
	}


	public void setBusAccount(String busAccount) {
		this.BusAccount = busAccount;
	}


	public String getBusName() {
		return BusName;
	}


	public void setBusName(String busName) {
		this.BusName = busName;
	}


	public String getComName() {
		return ComName;
	}


	public void setComName(String comName) {
		this.ComName = comName;
	}


	public Integer getComStock() {
		return ComStock;
	}


	public void setComStock(Integer comStock) {
		this.ComStock = comStock;
	}


	public String getComSort() {
		return ComSort;
	}


	public void setComSort(String comSort) {
		this.ComSort = comSort;
	}


	public String getComDescription() {
		return ComDescription;
	}


	public void setComDescription(String comDescription) {
		this.ComDescription = comDescription;
	}


	public Date getComValidity() {
		return ComValidity;
	}


	public void setComValidity(Date comValidity) {
		this.ComValidity = comValidity;
	}


	public Blob getComImage() {
		return ComImage;
	}


	public void setComImage(Blob comImage) {
		this.ComImage = comImage;
	}


	public Double getComPrice() {
		return ComPrice;
	}


	public void setComPrice(Double comPrice) {
		this.ComPrice = comPrice;
	}


	
	
	
	

}
