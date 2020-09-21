package _02_ShoppingSystem.CommodityList.model;

import java.io.Serializable;

public class MerchantBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String  BusAccount;
	private String  BusPassword;
	private String  BusEmail;
	
	public MerchantBean() {
	}

	
	public MerchantBean(String busAccount, String busPassword, String busEmail) {
		this.BusAccount = busAccount;
		this.BusPassword = busPassword;
		this.BusEmail = busEmail;
	}


	public String getBusAccount() {
		return BusAccount;
	}


	public void setBusAccount(String busAccount) {
		this.BusAccount = busAccount;
	}


	public String getBusPassword() {
		return BusPassword;
	}


	public void setBusPassword(String busPassword) {
		this.BusPassword = busPassword;
	}


	public String getBusEmail() {
		return BusEmail;
	}


	public void setBusEmail(String busEmail) {
		this.BusEmail = busEmail;
	}

}
