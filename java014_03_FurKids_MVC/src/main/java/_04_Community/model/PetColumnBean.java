package _04_Community.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petcolumn")
public class PetColumnBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String PCID;
	private String PCFID;
	private String PCTitle;
	private String PCContent;
	private Blob PCImage;
	private String PCImageName;
	private String PCFounder;
	private String PCAccount;
	private String PCViews;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String PCDateTime;

	public PetColumnBean() {

	}

	public PetColumnBean(String pCTitle, Blob pCImage, String pCContent, String pCFounder) {
		super();
		PCTitle = pCTitle;
		PCImage = pCImage;
		PCContent = pCContent;
		PCFounder = pCFounder;
	}

	public String getPCID() {
		return PCID;
	}

	public void setPCID(String pCID) {
		PCID = pCID;
	}

	public String getPCFID() {
		return PCFID;
	}

	public void setPCFID(String pCFID) {
		PCFID = pCFID;
	}

	public String getPCTitle() {
		return PCTitle;
	}

	public void setPCTitle(String pCTitle) {
		PCTitle = pCTitle;
	}

	public String getPCContent() {
		return PCContent;
	}

	public void setPCContent(String pCContent) {
		PCContent = pCContent;
	}

	public Blob getPCImage() {
		return PCImage;
	}

	public void setPCImage(Blob pCImage) {
		PCImage = pCImage;
	}

	public String getPCImageName() {
		return PCImageName;
	}

	public void setPCImageName(String pCImageName) {
		PCImageName = pCImageName;
	}

	public String getPCFounder() {
		return PCFounder;
	}

	public void setPCFounder(String pCFounder) {
		PCFounder = pCFounder;
	}

	public String getPCAccount() {
		return PCAccount;
	}

	public void setPCAccount(String pCAccount) {
		PCAccount = pCAccount;
	}

	public String getPCViews() {
		return PCViews;
	}

	public void setPCViews(String pCViews) {
		PCViews = pCViews;
	}


	public String getPCDateTime() {
		return PCDateTime;
	}

	public void setPCDateTime(String pCDateTime) {
		PCDateTime = pCDateTime;
	}

}
