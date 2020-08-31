package _01_Member.Registration.model;

import java.io.Serializable;

public class MerchantChildBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String busAccount;
	private Integer busChildNo;
	private String busName;
	private String busTel;
	private String busAddress;
	private String busDescription;
	private String busPhoto;

	public MerchantChildBean() {
	}

	public MerchantChildBean(String busAccount, Integer busChildNo, String busName, String busTel, String busAddress,
			String busDescription, String busPhoto) {
		super();
		this.busAccount = busAccount;
		this.busChildNo = busChildNo;
		this.busName = busName;
		this.busTel = busTel;
		this.busAddress = busAddress;
		this.busDescription = busDescription;
		this.busPhoto = busPhoto;
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

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
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

	public String getBusPhoto() {
		return busPhoto;
	}

	public void setBusPhoto(String busPhoto) {
		this.busPhoto = busPhoto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
