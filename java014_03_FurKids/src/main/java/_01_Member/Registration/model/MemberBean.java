package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="membraneregistration")
public class MemberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String cusAccount;
	private String cusPassword;
	private String cusName;
	private String cusNickName;
	private String cusGender;
	private Date cusBirthday;
	private String cusEmail;
	private String cusTel;
	private String cusAddress;
	private Blob cusPhoto;
	private String cusFileName;
	@Transient
	final private Integer CLASSIFY = 0;
	
	// 雙向一對多
	@OneToMany
	@JoinColumn(name = "cusAccount")
	Set<PetBean> pet = new LinkedHashSet<>();

	
	public MemberBean() {
	}

	public MemberBean(String cusAccount, String cusPassword, String cusName, String cusNickName, String cusGender,
			Date cusBirthday, String cusEmail, String cusTel, String cusAddress, Blob cusPhoto, String cusFileName) {
		super();
		this.cusAccount = cusAccount;
		this.cusPassword = cusPassword;
		this.cusName = cusName;
		this.cusNickName = cusNickName;
		this.cusGender = cusGender;
		this.cusBirthday = cusBirthday;
		this.cusEmail = cusEmail;
		this.cusTel = cusTel;
		this.cusAddress = cusAddress;
		this.cusPhoto = cusPhoto;
		this.cusFileName = cusFileName;
	}

	public String getCusAccount() {
		return cusAccount;
	}

	public void setCusAccount(String cusAccount) {
		this.cusAccount = cusAccount;
	}

	public String getCusPassword() {
		return cusPassword;
	}

	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	public String getCusNickName() {
		return cusNickName;
	}

	public void setCusNickName(String cusNickName) {
		this.cusNickName = cusNickName;
	}

	public String getCusGender() {
		return cusGender;
	}

	public void setCusGender(String cusGender) {
		this.cusGender = cusGender;
	}

	public Date getCusBirthday() {
		return cusBirthday;
	}

	public void setCusBirthday(Date cusBirthday) {
		this.cusBirthday = cusBirthday;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusTel() {
		return cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public Blob getCusPhoto() {
		return cusPhoto;
	}

	public void setCusPhoto(Blob cusPhoto) {
		this.cusPhoto = cusPhoto;
	}

	public String getCusFileName() {
		return cusFileName;
	}


	public void setCusFileName(String cusFileName) {
		this.cusFileName = cusFileName;
	}

	public Integer getCLASSIFY() {
		return CLASSIFY;
	}

	public Set<PetBean> getPet() {
		return pet;
	}

	public void setPet(Set<PetBean> pet) {
		this.pet = pet;
	}

	
}
