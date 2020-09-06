package _03_FriendlyService.model;

import java.io.Serializable;

	//封裝商家預約上架的服務
public class ConvenienceBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String busAccount;  //商家帳號
	private int busChildNo ;	//商家分店編號
	private String conItem;		//分類項目名稱
	private String conItemList;	//服務細項
	private String conDateTime;	//營業時間
	private String BusName;		//商家名稱
	private int busTel;   		//商家電話
	private String BusAddress;	//商家地址
	private String BusDescription; //商家描述
	private String BusPhoto; 		//商家照片
	private String BusEmail;	//商家MAIL
	
	public ConvenienceBean() {
	}

	public ConvenienceBean(String busAccount, int busChildNo, String conItem, String conItemList, String conDateTime,
			int busTel, String busAddress, String busDescription, String busPhoto) {
		super();
		this.busAccount = busAccount;
		this.busChildNo = busChildNo;
		this.conItem = conItem;
		this.conItemList = conItemList;
		this.conDateTime = conDateTime;
		this.busTel = busTel;
		BusAddress = busAddress;
		BusDescription = busDescription;
		BusPhoto = busPhoto;
	}

	public String getBusAccount() {
		return busAccount;
	}

	public void setBusAccount(String busAccount) {
		this.busAccount = busAccount;
	}

	public int getBusChildNo() {
		return busChildNo;
	}

	public void setBusChildNo(int busChildNo) {
		this.busChildNo = busChildNo;
	}

	public String getConItem() {
		return conItem;
	}

	public void setConItem(String conItem) {
		this.conItem = conItem;
	}

	public String getConItemList() {
		return conItemList;
	}

	
	public String getBusEmail() {
		return BusEmail;
	}

	public void setBusEmail(String busEmail) {
		BusEmail = busEmail;
	}

	public void setConItemList(String conItemList) {
		this.conItemList = conItemList;
	}

	public String getConDateTime() {
		return conDateTime;
	}

	public void setConDateTime(String conDateTime) {
		this.conDateTime = conDateTime;
	}
	
	public String getBusName() {
		return BusName;
	}

	public void setBusName(String busName) {
		BusName = busName;
	}

	public int getBusTel() {
		return busTel;
	}

	public void setBusTel(int busTel) {
		this.busTel = busTel;
	}

	public String getBusAddress() {
		return BusAddress;
	}

	public void setBusAddress(String busAddress) {
		BusAddress = busAddress;
	}

	public String getBusDescription() {
		return BusDescription;
	}

	public void setBusDescription(String busDescription) {
		BusDescription = busDescription;
	}

	public String getBusPhoto() {
		return BusPhoto;
	}

	public void setBusPhoto(String busPhoto) {
		BusPhoto = busPhoto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
