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
	private String petBreed;
	private String petVariety;
	private Blob petPhoto;
	private String petFileName;
	
	public PetBean() {
	}

	public PetBean(String cusAccount, String petName, String petGender, Date petBirthday,
			String petBreed, String petVariety, Blob petPhoto, String petFileName) {
		super();
		this.cusAccount = cusAccount;
		this.petName = petName;
		this.petGender = petGender;
		this.petBirthday = petBirthday;
		this.petBreed = petBreed;
		this.petVariety = petVariety;
		this.petPhoto = petPhoto;
		this.petFileName = petFileName;
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

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
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

	public String getPetFileName() {
		return petFileName;
	}

	public void setPetFileName(String petFileName) {
		this.petFileName = petFileName;
	}

}
