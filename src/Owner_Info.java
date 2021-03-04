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
public class Owner_Info {
	@Id
	private String Ownerid;
	@Column
	private String Owner_Name;
	@Column
	private String Owner_Contact;
	@OneToMany(fetch=FetchType.LAZY, targetEntity=Horse_Info.class, cascade=CascadeType.ALL)
	@JoinColumn(name="Ownerid",referencedColumnName = "Ownerid")
	private Set children;
	public String getOwnerid() {
		return Ownerid;
	}
	public void setOwnerid(String ownerid) {
		Ownerid = ownerid;
	}
	public String getOwner_Name() {
		return Owner_Name;
	}
	public void setOwner_Name(String owner_Name) {
		Owner_Name = owner_Name;
	}
	public String getOwner_Contact() {
		return Owner_Contact;
	}
	public void setOwner_Contact(String owner_Contact) {
		Owner_Contact = owner_Contact;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	
}
