package _03_FriendlyService.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class ReservationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resID;
	private String cusAccount;
	private String conAccount;
	private Integer busChildNo;
	private String busChildName;
	private String busChildAddress;
	private String resDate;
	private String resTime;
	
	@OneToMany(mappedBy="reservationBean")
	private Set<ReservationChildBean> reservationChildBean = new LinkedHashSet<>();

	
	
	public ReservationBean() {
		
	}



	public ReservationBean(Integer resID, String cusAccount, String conAccount, Integer busChildNo, String busChildName,
			String busChildAddress, String resDate, String resTime, Set<ReservationChildBean> reservationChildBean) {
		super();
		this.resID = resID;
		this.cusAccount = cusAccount;
		this.conAccount = conAccount;
		this.busChildNo = busChildNo;
		this.busChildName = busChildName;
		this.busChildAddress = busChildAddress;
		this.resDate = resDate;
		this.resTime = resTime;
		this.reservationChildBean = reservationChildBean;
	}



	public Integer getResID() {
		return resID;
	}



	public void setResID(Integer resID) {
		this.resID = resID;
	}



	public String getCusAccount() {
		return cusAccount;
	}



	public void setCusAccount(String cusAccount) {
		this.cusAccount = cusAccount;
	}



	public String getConAccount() {
		return conAccount;
	}



	public void setConAccount(String conAccount) {
		this.conAccount = conAccount;
	}



	public Integer getBusChildNo() {
		return busChildNo;
	}



	public void setBusChildNo(Integer busChildNo) {
		this.busChildNo = busChildNo;
	}



	public String getBusChildName() {
		return busChildName;
	}



	public void setBusChildName(String busChildName) {
		this.busChildName = busChildName;
	}



	public String getBusChildAddress() {
		return busChildAddress;
	}



	public void setBusChildAddress(String busChildAddress) {
		this.busChildAddress = busChildAddress;
	}



	public String getResDate() {
		return resDate;
	}



	public void setResDate(String resDate) {
		this.resDate = resDate;
	}



	public String getResTime() {
		return resTime;
	}



	public void setResTime(String resTime) {
		this.resTime = resTime;
	}



	public Set<ReservationChildBean> getReservationChildBean() {
		return reservationChildBean;
	}



	public void setReservationChildBean(Set<ReservationChildBean> reservationChildBean) {
		this.reservationChildBean = reservationChildBean;
	}
	
}