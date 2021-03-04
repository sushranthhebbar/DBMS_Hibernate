package HorseRacingManagement.IMT2017042;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Horse_Info {
	@Id
	private String Horseid;
	@Column
	private int Age;
	@Column
	private int Weight;
	@Column
	private int No_of_wins;
	@Column
	private int No_of_races;
	@Column
	private String Best_time;
	@Column
	private String Worst_time;
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=Race_Info.class, cascade=CascadeType.ALL)
	@JoinColumn(name="Horseid",referencedColumnName = "Horseid")
	private Set children;
	
	@ManyToOne
	@JoinColumn(name="Sponsorid")
	private Sponsor_Info sponsor;
	
	@ManyToOne
	@JoinColumn(name="Ownerid")
	private Owner_Info owner;
	
	
	public Sponsor_Info getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor_Info sponsor) {
		this.sponsor = sponsor;
	}
	public Owner_Info getOwner() {
		return owner;
	}
	public void setOwner(Owner_Info owner) {
		this.owner = owner;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	public String getHorseid() {
		return Horseid;
	}
	public void setHorseid(String horseid) {
		Horseid = horseid;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	public int getNo_of_wins() {
		return No_of_wins;
	}
	public void setNo_of_wins(int no_of_wins) {
		No_of_wins = no_of_wins;
	}
	public int getNo_of_races() {
		return No_of_races;
	}
	public void setNo_of_races(int no_of_races) {
		No_of_races = no_of_races;
	}
	public String getBest_time() {
		return Best_time;
	}
	public void setBest_time(String best_time) {
		Best_time = best_time;
	}
	public String getWorst_time() {
		return Worst_time;
	}
	public void setWorst_time(String worst_time) {
		Worst_time = worst_time;
	}
	
}
