package _01_Member.Registration.model;


import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="merchantregistration")
public class MerchantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String busAccount;
	private String busPassword;
	@Transient
	private String confirmPassword;
	private String busName;
	private String busTel;
	private String busAddress;
	private String busDescription;
	private Blob busPhoto;
	private String busFileName;
	@Transient
	MultipartFile merchantMultipartFile;
	
	@Transient
	final private Integer CLASSIFY = 1;
	
	@OneToMany(mappedBy = "merchantBean", cascade = CascadeType.ALL)
	private Set<MerchantChildBean> merchantChild = new LinkedHashSet<>();

	public MerchantBean() {
		super();
	}

	public MerchantBean(String busAccount, String busPassword, String confirmPassword, String busName, String busTel,
			String busAddress, String busDescription, Blob busPhoto, String busFileName) {
		super();
		this.busAccount = busAccount;
		this.busPassword = busPassword;
		this.confirmPassword = confirmPassword;
		this.busName = busName;
		this.busTel = busTel;
		this.busAddress = busAddress;
		this.busDescription = busDescription;
		this.busPhoto = busPhoto;
		this.busFileName = busFileName;
	}

	public MerchantBean(String busAccount, String busPassword, String confirmPassword, String busName, String busTel,
			String busAddress, String busDescription, Blob busPhoto, String busFileName,
			MultipartFile merchantMultipartFile) {
		super();
		this.busAccount = busAccount;
		this.busPassword = busPassword;
		this.confirmPassword = confirmPassword;
		this.busName = busName;
		this.busTel = busTel;
		this.busAddress = busAddress;
		this.busDescription = busDescription;
		this.busPhoto = busPhoto;
		this.busFileName = busFileName;
		this.merchantMultipartFile = merchantMultipartFile;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
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

	public MultipartFile getMerchantMultipartFile() {
		return merchantMultipartFile;
	}

	public void setMerchantMultipartFile(MultipartFile merchantMultipartFile) {
		this.merchantMultipartFile = merchantMultipartFile;
	}

	public Set<MerchantChildBean> getMerchantChild() {
		return merchantChild;
	}

	public void setMerchantChild(Set<MerchantChildBean> merchantChild) {
		this.merchantChild = merchantChild;
	}

	public Integer getCLASSIFY() {
		return CLASSIFY;
	}

}
