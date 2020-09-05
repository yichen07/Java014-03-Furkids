package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;

public class MerchantChildBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String busAccount;
	private Integer busChildNo;		// Auto_Increment 自增欄位。
	private String busChildName;
	private String busChildTel;
	private String busChildAddress;
	private String busChildDescription;
	private Blob busChildPhoto;
	private String busChildFileName;

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
