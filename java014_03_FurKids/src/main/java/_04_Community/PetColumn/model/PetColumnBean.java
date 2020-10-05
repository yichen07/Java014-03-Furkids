package _04_Community.PetColumn.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PetColumn")
public class PetColumnBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String PCID;
	private String PCFID;
	private String PCTitle;
	private String PCContent;
	private Blob PCImage;
	private String PCFounder;
	private String PCAccount;
	private String PCViews;
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
