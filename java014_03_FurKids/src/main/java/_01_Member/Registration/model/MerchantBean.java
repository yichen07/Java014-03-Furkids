package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;

public class MerchantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String busAccount;
	private String busPassword;
	private String busName;
	private String busEmail;
	private Blob busPhoto;
	private String busFileName;
	
	
	public MerchantBean() {
	}

	public MerchantBean(String busAccount, String busPassword, String busName, String busEmail, Blob busPhoto,
			String busFileName) {
		super();
		this.busAccount = busAccount;
		this.busPassword = busPassword;
		this.busName = busName;
		this.busEmail = busEmail;
		this.busPhoto = busPhoto;
		this.busFileName = busFileName;
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
	
	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public Blob getBusPhoto() {
		return busPhoto;
	}

	public void setBusPhoto(Blob busPhoto) {
		this.busPhoto = busPhoto;
	}

	public String getBusFileName() {
		return busFileName;
	}

	public void setBusFileName(String busFileName) {
		this.busFileName = busFileName;
	}
	
}
