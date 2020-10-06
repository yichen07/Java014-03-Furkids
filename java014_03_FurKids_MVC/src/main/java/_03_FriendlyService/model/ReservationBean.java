package _03_FriendlyService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private String resNote;
	
	@OneToMany(mappedBy="reservationBean",cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	private List<ReservationChildBean> reservationChildBean = new ArrayList<>();

	
	
	public ReservationBean() {
		
	}



	public ReservationBean(Integer resID, String cusAccount, String conAccount, Integer busChildNo, String busChildName,
			String busChildAddress, String resDate, String resTime, String resNote, List<ReservationChildBean> reservationChildBean) {
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


	public List<ReservationChildBean> getReservationChildBean() {
		return reservationChildBean;
	}



	public void setReservationChildBean(List<ReservationChildBean> reservationChildBean) {
		this.reservationChildBean = reservationChildBean;
	}



	public String getResNote() {
		return resNote;
	}



	public void setResNote(String resNote) {
		this.resNote = resNote;
	}
	
}