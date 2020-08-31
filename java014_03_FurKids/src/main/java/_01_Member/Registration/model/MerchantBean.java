package _01_Member.Registration.model;

import java.io.Serializable;

public class MerchantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String busAccount;
	private String busPassword;
	private String busEmail;
	
	public MerchantBean() {
	}

	public MerchantBean(String busAccount, String busPassword, String busEmail) {
		super();
		this.busAccount = busAccount;
		this.busPassword = busPassword;
		this.busEmail = busEmail;
	}

	public String getBusAccount() {
		return busAccount;
	}

	public void setBusAccount(String busAccount) {
		this.busAccount = busAccount;
	}

	public String getBusPassword() {
		return busPassword;
	}

	public void setBusPassword(String busPassword) {
		this.busPassword = busPassword;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
