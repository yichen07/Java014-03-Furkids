package _01_Member.Registration.model;


import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="merchantregistration")
public class MerchantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String busAccount;
	private String busPassword;
	private String busName;
	private String busEmail;
	private String busTel;
	private String busAddress;
	private String busDescription;
	private Blob busPhoto;
	private String busFileName;
	
	@Transient
	private Integer CHECKNO = 2;
	
	public MerchantBean() {
	}


	public MerchantBean(String busAccount, String busPassword, String busName, String busEmail, String busTel,
			String busAddress, String busDescription, Blob busPhoto, String busFileName) {
		super();
		this.busAccount = busAccount;
		this.busPassword = busPassword;
		this.busName = busName;
		this.busEmail = busEmail;
		this.busTel = busTel;
		this.busAddress = busAddress;
		this.busDescription = busDescription;
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


	public Integer getCHECKNO() {
		return CHECKNO;
	}


	public void setCHECKNO(Integer cHECKNO) {
		CHECKNO = cHECKNO;
	}
	
	
}
