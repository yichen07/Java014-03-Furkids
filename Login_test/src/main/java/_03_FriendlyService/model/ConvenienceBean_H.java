package _03_FriendlyService.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import _01_Member.Registration.model.MerchantChildBean;



	//封裝商家預約上架的服務

@Entity
@Table(name="convenience")
public class ConvenienceBean_H implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer busChildNo ;	//商家分店編號
	private String busAccount;  //商家帳號
	private String conItem;		//分類項目名稱
	private String conItemList;	//服務細項
	private String conCloseDay;	//週休日
	private String conOpenTime; //開始營業時間
	private String conCloseTime; //結束營業時間
	
	//一對一商家分店
	@OneToOne
	@JoinColumn(name="busChildNo")
	private MerchantChildBean merchantChildBean;
	
		
	public ConvenienceBean_H() {
	}


	public ConvenienceBean_H(Integer busChildNo, String busAccount, String conItem, String conItemList,
			String conCloseDay, String conOpenTime, String conCloseTime) {
		super();
		this.busChildNo = busChildNo;
		this.busAccount = busAccount;
		this.conItem = conItem;
		this.conItemList = conItemList;
		this.conCloseDay = conCloseDay;
		this.conOpenTime = conOpenTime;
		this.conCloseTime = conCloseTime;
	}


	public Integer getBusChildNo() {
		return busChildNo;
	}


	public void setBusChildNo(Integer busChildNo) {
		this.busChildNo = busChildNo;
	}


	public String getBusAccount() {
		return busAccount;
	}


	public void setBusAccount(String busAccount) {
		this.busAccount = busAccount;
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


	public void setConItemList(String conItemList) {
		this.conItemList = conItemList;
	}


	public String getConCloseDay() {
		return conCloseDay;
	}


	public void setConCloseDay(String conCloseDay) {
		this.conCloseDay = conCloseDay;
	}


	public String getConOpenTime() {
		return conOpenTime;
	}


	public void setConOpenTime(String conOpenTime) {
		this.conOpenTime = conOpenTime;
	}


	public String getConCloseTime() {
		return conCloseTime;
	}


	public void setConCloseTime(String conCloseTime) {
		this.conCloseTime = conCloseTime;
	}


	public MerchantChildBean getMerchantChildBean() {
		return merchantChildBean;
	}


	public void setMerchantChildBean(MerchantChildBean merchantChildBean) {
		this.merchantChildBean = merchantChildBean;
	}



	
	

	

	
}
