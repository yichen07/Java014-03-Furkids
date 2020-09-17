package _02_ShoppingSystem.CommodityList.model;

import java.io.Serializable;
import java.sql.Blob;

public class MerchantChildBean implements Serializable {
	private String  BusAccount;
	private Integer BusChildNo;
	private String  BusName;
	private String  BusTel;
	private String  BusAddress;
	private String  BusDescription;
	private Blob    BusPhoto;
	private String  BusFileName;
	
	public MerchantChildBean(String busAccount, Integer busChildNo, String busName, String busTel, String busAddress,
			String busDescription, Blob busPhoto, String busFileName) {
		this.BusAccount = busAccount;
		this.BusChildNo = busChildNo;
		this.BusName = busName;
		this.BusTel = busTel;
		this.BusAddress = busAddress;
		this.BusDescription = busDescription;
		this.BusPhoto = busPhoto;
		this.BusFileName = busFileName;
	}

	public MerchantChildBean() {
	}
	
	public String getBusAccount() {
		return BusAccount;
	}

	public void setBusAccount(String busAccount) {
		this.BusAccount = busAccount;
	}

	public Integer getBusChildNo() {
		return BusChildNo;
	}

	public void setBusChildNo(Integer busChildNo) {
		this.BusChildNo = busChildNo;
	}

	public String getBusName() {
		return BusName;
	}

	public void setBusName(String busName) {
		this.BusName = busName;
	}

	public String getBusTel() {
		return BusTel;
	}

	public void setBusTel(String busTel) {
		this.BusTel = busTel;
	}

	public String getBusAddress() {
		return BusAddress;
	}

	public void setBusAddress(String busAddress) {
		this.BusAddress = busAddress;
	}

	public String getBusDescription() {
		return BusDescription;
	}

	public void setBusDescription(String busDescription) {
		this.BusDescription = busDescription;
	}

	public Blob getBusPhoto() {
		return BusPhoto;
	}

	public void setBusPhoto(Blob busPhoto) {
		this.BusPhoto = busPhoto;
	}

	public String getBusFileName() {
		return BusFileName;
	}

	public void setBusFileName(String busFileName) {
		this.BusFileName = busFileName;
	}

	

}
