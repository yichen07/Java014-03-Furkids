package _03_listProducts.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Product")
public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer p_Id;				//商品id
	private String p_Category;			//商品類別
	private String p_Name;				//商品名稱
	private Blob p_Cover;				//商品封面
	private String p_FileName;			//商品封面檔名
	private int p_Pdqty;				//最近進貨數量
	private int p_Pdsum;				//最近進貨金額
	private int p_Stock;				//現有庫存量
	private int p_Sdqty;				//銷貨數量
	@Min(value=0, message="數值必須大於0")
	private Double p_Price;				//單價
	private Blob p_Pic1;				//商品展示圖1
	private String p_FileName1;			//商品展示圖1檔名
	private Blob p_Pic2;				//商品展示圖2
	private String p_FileName2;			//商品展示圖2檔名
	private Blob p_Pic3;				//商品展示圖3
	private String p_FileName3;			//商品展示圖3檔
	private String p_Note;				//產品敘述
	private Timestamp p_CreateTime;		//建檔時間
	private Timestamp p_UpdataTime;		//修改時間

	@Transient
	private MultipartFile productImage1;//取圖檔用
	@Transient
	private MultipartFile productImage2;//取圖檔用
	@Transient
	private MultipartFile productImage3;//取圖檔用
	@Transient
	private MultipartFile p_Cover1;//取圖檔用
	
	
	public ProductBean(Integer p_Id, String p_Category, String p_Name, Blob p_Cover, String p_FileName, int p_Pdqty,
			int p_Pdsum, int p_Stock, int p_Sdqty, Double p_Price, Blob p_Pic1, String p_FileName1, Blob p_Pic2,
			String p_FileName2, Blob p_Pic3, String p_FileName3, String p_Note, Timestamp p_CreateTime,
			Timestamp p_UpdataTime, MultipartFile productImage1, MultipartFile productImage2,
			MultipartFile productImage3, MultipartFile p_Cover1) {
		super();
		this.p_Id = p_Id;
		this.p_Category = p_Category;
		this.p_Name = p_Name;
		this.p_Cover = p_Cover;
		this.p_FileName = p_FileName;
		this.p_Pdqty = p_Pdqty;
		this.p_Pdsum = p_Pdsum;
		this.p_Stock = p_Stock;
		this.p_Sdqty = p_Sdqty;
		this.p_Price = p_Price;
		this.p_Pic1 = p_Pic1;
		this.p_FileName1 = p_FileName1;
		this.p_Pic2 = p_Pic2;
		this.p_FileName2 = p_FileName2;
		this.p_Pic3 = p_Pic3;
		this.p_FileName3 = p_FileName3;
		this.p_Note = p_Note;
		this.p_CreateTime = p_CreateTime;
		this.p_UpdataTime = p_UpdataTime;
		this.productImage1 = productImage1;
		this.productImage2 = productImage2;
		this.productImage3 = productImage3;
		this.p_Cover1 = p_Cover1;
	}
	
	public ProductBean() {
		
	}
	
	public Integer getP_Id() {
		return p_Id;
	}
	public void setP_Id(Integer p_Id) {
		this.p_Id = p_Id;
	}
	public String getP_Category() {
		return p_Category;
	}
	public void setP_Category(String p_Category) {
		this.p_Category = p_Category;
	}
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
	public Blob getP_Cover() {
		return p_Cover;
	}
	public void setP_Cover(Blob p_Cover) {
		this.p_Cover = p_Cover;
	}
	public String getP_FileName() {
		return p_FileName;
	}
	public void setP_FileName(String p_FileName) {
		this.p_FileName = p_FileName;
	}
	public int getP_Pdqty() {
		return p_Pdqty;
	}
	public void setP_Pdqty(int p_Pdqty) {
		this.p_Pdqty = p_Pdqty;
	}
	public int getP_Pdsum() {
		return p_Pdsum;
	}
	public void setP_Pdsum(int p_Pdsum) {
		this.p_Pdsum = p_Pdsum;
	}
	public int getP_Stock() {
		return p_Stock;
	}
	public void setP_Stock(int p_Stock) {
		this.p_Stock = p_Stock;
	}
	public int getP_Sdqty() {
		return p_Sdqty;
	}
	public void setP_Sdqty(int p_Sdqty) {
		this.p_Sdqty = p_Sdqty;
	}
	public Double getP_Price() {
		return p_Price;
	}
	public void setP_Price(Double p_Price) {
		this.p_Price = p_Price;
	}
	public Blob getP_Pic1() {
		return p_Pic1;
	}
	public void setP_Pic1(Blob p_Pic1) {
		this.p_Pic1 = p_Pic1;
	}
	public String getP_FileName1() {
		return p_FileName1;
	}
	public void setP_FileName1(String p_FileName1) {
		this.p_FileName1 = p_FileName1;
	}
	public Blob getP_Pic2() {
		return p_Pic2;
	}
	public void setP_Pic2(Blob p_Pic2) {
		this.p_Pic2 = p_Pic2;
	}
	public String getP_FileName2() {
		return p_FileName2;
	}
	public void setP_FileName2(String p_FileName2) {
		this.p_FileName2 = p_FileName2;
	}
	public Blob getP_Pic3() {
		return p_Pic3;
	}
	public void setP_Pic3(Blob p_Pic3) {
		this.p_Pic3 = p_Pic3;
	}
	public String getP_FileName3() {
		return p_FileName3;
	}
	public void setP_FileName3(String p_FileName3) {
		this.p_FileName3 = p_FileName3;
	}
	public String getP_Note() {
		return p_Note;
	}
	public void setP_Note(String p_Note) {
		this.p_Note = p_Note;
	}
	public Timestamp getP_CreateTime() {
		return p_CreateTime;
	}
	public void setP_CreateTime(Timestamp p_CreateTime) {
		this.p_CreateTime = p_CreateTime;
	}
	public Timestamp getP_UpdataTime() {
		return p_UpdataTime;
	}
	public void setP_UpdataTime(Timestamp p_UpdataTime) {
		this.p_UpdataTime = p_UpdataTime;
	}
	public MultipartFile getProductImage1() {
		return productImage1;
	}
	public void setProductImage1(MultipartFile productImage1) {
		this.productImage1 = productImage1;
	}
	public MultipartFile getProductImage2() {
		return productImage2;
	}
	public void setProductImage2(MultipartFile productImage2) {
		this.productImage2 = productImage2;
	}
	public MultipartFile getProductImage3() {
		return productImage3;
	}
	public void setProductImage3(MultipartFile productImage3) {
		this.productImage3 = productImage3;
	}
	public MultipartFile getP_Cover1() {
		return p_Cover1;
	}
	public void setP_Cover1(MultipartFile p_Cover1) {
		this.p_Cover1 = p_Cover1;
	}
	
}
