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
	private String resSpecies;
	private String resVariety;
	private Integer resQuantity;
	private String resNote;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="resID" , insertable=false ,updatable=false)
	private ReservationBean reservationBean;

	
	
	public ReservationChildBean() {
	
	}

	public ReservationChildBean(Integer resChildID, Integer resID, String resSpecies, String resVariety,
			Integer resQuantity, String resNote, ReservationBean reservationBean) {
		super();
		this.resChildID = resChildID;
		this.resID = resID;
		this.resSpecies = resSpecies;
		this.resVariety = resVariety;
		this.resQuantity = resQuantity;
		this.resNote = resNote;
		this.reservationBean = reservationBean;
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

	public Integer getResQuantity() {
		return resQuantity;
	}

	public void setResQuantity(Integer resQuantity) {
		this.resQuantity = resQuantity;
	}

	public String getResNote() {
		return resNote;
	}

	public void setResNote(String resNote) {
		this.resNote = resNote;
	}

	public ReservationBean getReservationBean() {
		return reservationBean;
	}

	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}
	
	
	
}
