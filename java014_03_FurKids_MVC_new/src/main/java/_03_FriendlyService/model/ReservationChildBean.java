package _03_FriendlyService.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservationchild")
public class ReservationChildBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resChildID;
	private Integer resID;
	private String resName;
	private String resSpecies;
	private String resVariety;
	
	
	@ManyToOne
	@JoinColumn(name="resID" , insertable=false ,updatable=false)
	private ReservationBean reservationBean;

	
	
	public ReservationChildBean() {
	
	}
	
	
	public ReservationChildBean(String resName, String resSpecies, String resVariety) {
		super();
		this.resName = resName;
		this.resSpecies = resSpecies;
		this.resVariety = resVariety;
	}
	
	public ReservationChildBean(Integer resChildID, Integer resID, String resName, String resSpecies, String resVariety) {
		super();
		this.resChildID = resChildID;
		this.resID = resID;
		this.resName = resName;
		this.resSpecies = resSpecies;
		this.resVariety = resVariety;
	}


	public Integer getResChildID() {
		return resChildID;
	}

	public void setResChildID(Integer resChildID) {
		this.resChildID = resChildID;
	}

	public Integer getResID() {
		return resID;
	}

	public void setResID(Integer resID) {
		this.resID = resID;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResSpecies() {
		return resSpecies;
	}

	public void setResSpecies(String resSpecies) {
		this.resSpecies = resSpecies;
	}

	public String getResVariety() {
		return resVariety;
	}

	public void setResVariety(String resVariety) {
		this.resVariety = resVariety;
	}

	public ReservationBean getReservationBean() {
		return reservationBean;
	}

	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}
	
	
	
}
