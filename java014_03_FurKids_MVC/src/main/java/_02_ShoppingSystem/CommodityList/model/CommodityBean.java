package _02_ShoppingSystem.CommodityList.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.springframework.web.multipart.MultipartFile;

import _01_Member.Registration.model.MerchantBean;

// 本類別封裝單筆書籍資料
@Entity
@Table(name="CommodityList")
public class CommodityBean implements Serializable {
	private static final long serialVersionUID = 1L;  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ComId;
//	private String BusName;
	private String ComName;   //商品名稱
	private String fileName;
	private Integer ComStock;
	private String ComSort;
	private String ComDescription;
	private Date ComValidity;  //有效期限
	private Blob ComImage; //商品圖片 
	@Min(value=0, message="數值必須大於0") 
	private Double ComPrice;  //單價
	@Transient
	private String BusAccount; 

	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="BusAccount")  
	private MerchantBean merchantBean;

	@Transient
	MultipartFile productImage;

	
	public CommodityBean(Integer comId, String busAccount, String comName, Integer comStock,
			String comSort, String comDescription, Date comValidity, Blob comImage, Double comPrice) {
		this.ComId = comId;
		this.BusAccount = busAccount;
//		this.BusName = busName;
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


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
//  	public String getBusName() {
//	    	return BusName;
//    }


//	public void setBusName(String busName) {
//		this.BusName = busName;
//	}


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


	public MerchantBean getMerchantBean() {
		return merchantBean;
	}

	public void setMerchantBean(MerchantBean cb) {
		this.merchantBean = cb;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}	
	
	
	

}
