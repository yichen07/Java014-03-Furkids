package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;

public class MerchantChildBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String busAccount;
	private Integer busChildNo;		// Auto_Increment 自增欄位。
	private String busChildName;
	private String busTel;
	private String busAddress;
	private String busDescription;
	private Blob busChildPhoto;
	private String busChildFileName;

	public MerchantChildBean() {
	}

	public MerchantChildBean(String busAccount, Integer busChildNo, String busChildName, String busTel,
			String busAddress, String busDescription, Blob busChildPhoto, String busChildFileName) {
		super();
		this.busAccount = busAccount;
		this.busChildNo = busChildNo;
		this.busChildName = busChildName;
		this.busTel = busTel;
		this.busAddress = busAddress;
		this.busDescription = busDescription;
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

	public String getBusTel() {
		return busTel;
	}

	public void setBusTel(String busTel) {
		this.busTel = busTel;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}

	public String getBusDescription() {
		return busDescription;
	}

	public void setBusDescription(String busDescription) {
		this.busDescription = busDescription;
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
