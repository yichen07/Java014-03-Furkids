package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import _03_FriendlyService.model.ConvenienceBean_H;

@Entity
@Table(name="merchantchildregistration")
public class MerchantChildBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busChildNo;		// Auto_Increment 自增欄位。
	private String busAccount;
	private String busChildName;
	private String busChildTel;
	private String busChildAddress;
	private String busChildDescription;
	private Blob busChildPhoto;
	private String busChildFileName;
	
	
	//雙向一對一
	@OneToOne
	@JoinColumn(name="busChildNo")
	private ConvenienceBean_H convenienceBean_H;
	
	//多對一商家註冊
	@ManyToOne
	@JoinColumn(name="busAccount" , insertable=false ,updatable=false)
	private MerchantBean merchantbean;

	public MerchantChildBean() {
	}

	public MerchantChildBean(String busAccount, Integer busChildNo, String busChildName, String busChildTel,
			String busChildAddress, String busChildDescription, Blob busChildPhoto, String busChildFileName) {
		super();
		this.busAccount = busAccount;
		this.busChildNo = busChildNo;
		this.busChildName = busChildName;
		this.busChildTel = busChildTel;
		this.busChildAddress = busChildAddress;
		this.busChildDescription = busChildDescription;
		this.busChildPhoto = busChildPhoto;
		this.busChildFileName = busChildFileName;
	
	}
	
	public ConvenienceBean_H getConvenienceBean_H() {
		return convenienceBean_H;
	}

	public void setConvenienceBean_H(ConvenienceBean_H convenienceBean_H) {
		this.convenienceBean_H = convenienceBean_H;
	}

	public MerchantBean getMerchantbean() {
		return merchantbean;
	}

	public void setMerchantbean(MerchantBean merchantbean) {
		this.merchantbean = merchantbean;
	}

	public String getBusAccount() {
		return busAccount;
	}

	public void setBusAccount(String busAccount) {
		this.busAccount = busAccount;
	}

	public Integer getBusChildNo() {
		return busChildNo;
	}

	public void setBusChildNo(Integer busChildNo) {
		this.busChildNo = busChildNo;
	}

	public String getBusChildName() {
		return busChildName;
	}

	public void setBusChildName(String busChildName) {
		this.busChildName = busChildName;
	}

	public String getBusChildTel() {
		return busChildTel;
	}

	public void setBusChildTel(String busChildTel) {
		this.busChildTel = busChildTel;
	}

	public String getBusChildAddress() {
		return busChildAddress;
	}

	public void setBusChildAddress(String busChildAddress) {
		this.busChildAddress = busChildAddress;
	}

	public String getBusChildDescription() {
		return busChildDescription;
	}

	public void setBusChildDescription(String busChildDescription) {
		this.busChildDescription = busChildDescription;
	}

	public Blob getBusChildPhoto() {
		return busChildPhoto;
	}

	public void setBusChildPhoto(Blob busChildPhoto) {
		this.busChildPhoto = busChildPhoto;
	}

	public String getBusChildFileName() {
		return busChildFileName;
	}

	public void setBusChildFileName(String busChildFileName) {
		this.busChildFileName = busChildFileName;
	}

	
}
