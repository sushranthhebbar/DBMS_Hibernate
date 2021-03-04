package HorseRacingManagement.IMT2017042;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Race_Info {
	@Id
	private String Raceid;
	@Column
	private String Track_Name;
	@Column
	private String Track_Distance;
	@Column
	private String Time;
	@Column
	private String Position;
	@Column
	private String Competition_Name;

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Horse_Info.class)
	@JoinColumn(name="Horseid")
	private Horse_Info parent;
	
	
	public Horse_Info getParent() {
		return parent;
	}
	public void setParent(Horse_Info parent) {
		this.parent = parent;
	}
	public String getRaceid() {
		return Raceid;
	}
	public void setRaceid(String raceid) {
		Raceid = raceid;
	}
	public String getTrack_Name() {
		return Track_Name;
	}
	public void setTrack_Name(String track_Name) {
		Track_Name = track_Name;
	}
	public String getTrack_Distance() {
		return Track_Distance;
	}
	public void setTrack_Distance(String track_Distance) {
		Track_Distance = track_Distance;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getCompetition_Name() {
		return Competition_Name;
	}
	public void setCompetition_Name(String competition_Name) {
		Competition_Name = competition_Name;
	}
	
	

}
