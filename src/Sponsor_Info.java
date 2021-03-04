package HorseRacingManagement.IMT2017042;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Sponsor_Info {
	@Id
	private String 	Sponsorid;
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	@Column
	private String Sponsor_name;
	@Column
	private String Sponsor_contact;
	
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=Horse_Info.class)
	@JoinColumn(name="Sponsorid",referencedColumnName = "Sponsorid")
	private Set children;
	
	public String getSponsorid() {
		return Sponsorid;
	}
	public void setSponsorid(String sponsorid) {
		Sponsorid = sponsorid;
	}
	public String getSponsor_name() {
		return Sponsor_name;
	}
	public void setSponsor_name(String sponsor_name) {
		Sponsor_name = sponsor_name;
	}
	public String getSponsor_contact() {
		return Sponsor_contact;
	}
	public void setSponsor_contact(String sponsor_contact) {
		Sponsor_contact = sponsor_contact;
	}

}
