package _01_Member.Registration.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="petregistration")
public class PetBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer petID;
	@Transient
	private String cusAccount;
	
	private String petName;
	private String petGender;
	private Date petBirthday;
	private String petBreed;
	private String petVariety;
	private Blob petPhoto;
	private String petFileName;
	@Transient
	MultipartFile petMultipartFile;
	
	// 雙向多對一
	@ManyToOne
	@JoinColumn(name = "cusAccount")
	private MemberBean memberBean;

	public PetBean() {
		super();
	}

	public PetBean(Integer petID, String cusAccount, String petName, String petGender, Date petBirthday,
			String petBreed, String petVariety, Blob petPhoto, String petFileName) {
		super();
		this.petID = petID;
		this.cusAccount = cusAccount;
		this.petName = petName;
		this.petGender = petGender;
		this.petBirthday = petBirthday;
		this.petBreed = petBreed;
		this.petVariety = petVariety;
		this.petPhoto = petPhoto;
		this.petFileName = petFileName;
	}

	public PetBean(Integer petID, String cusAccount, String petName, String petGender, Date petBirthday,
			String petBreed, String petVariety, Blob petPhoto, String petFileName, MultipartFile petMultipartFile) {
		super();
		this.petID = petID;
		this.cusAccount = cusAccount;
		this.petName = petName;
		this.petGender = petGender;
		this.petBirthday = petBirthday;
		this.petBreed = petBreed;
		this.petVariety = petVariety;
		this.petPhoto = petPhoto;
		this.petFileName = petFileName;
		this.petMultipartFile = petMultipartFile;
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

	public MultipartFile getPetMultipartFile() {
		return petMultipartFile;
	}

	public void setPetMultipartFile(MultipartFile petMultipartFile) {
		this.petMultipartFile = petMultipartFile;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

}
