package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class PetBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer petID;
	private String cusAccount;
	private String petName;
	private String petGender;
	private Date petBirthday;
	private String petBread;
	private String petVariety;
	private Blob petPhoto;
	
	public PetBean() {
	}

	public PetBean(Integer petID, String cusAccount, String petName, String petGender, Date petBirthday,
			String petBread, String petVariety, Blob petPhoto) {
		super();
		this.petID = petID;
		this.cusAccount = cusAccount;
		this.petName = petName;
		this.petGender = petGender;
		this.petBirthday = petBirthday;
		this.petBread = petBread;
		this.petVariety = petVariety;
		this.petPhoto = petPhoto;
	}

	public Integer getPetID() {
		return petID;
	}

	public void setPetID(Integer petID) {
		this.petID = petID;
	}

	public String getCusAccount() {
		return cusAccount;
	}

	public void setCusAccount(String cusAccount) {
		this.cusAccount = cusAccount;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public Date getPetBirthday() {
		return petBirthday;
	}

	public void setPetBirthday(Date petBirthday) {
		this.petBirthday = petBirthday;
	}

	public String getPetBread() {
		return petBread;
	}

	public void setPetBread(String petBread) {
		this.petBread = petBread;
	}

	public String getPetVariety() {
		return petVariety;
	}

	public void setPetVariety(String petVariety) {
		this.petVariety = petVariety;
	}

	public Blob getPetPhoto() {
		return petPhoto;
	}

	public void setPetPhoto(Blob petPhoto) {
		this.petPhoto = petPhoto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
