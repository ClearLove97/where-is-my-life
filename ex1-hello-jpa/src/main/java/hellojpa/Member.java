package hellojpa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	//기간 period
	@Embedded
	private Period workPeriod;
	//주소 address
	@Embedded
	private Address homeAddress;
	
	@ElementCollection
	@CollectionTable( name = "FAVORITE_FOOD", joinColumns = 
	@JoinColumn( name = "MEMBER_ID")
	)
	@Column( name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<String>();
	
	@ElementCollection
	@CollectionTable( name = "ADDRESS", joinColumns = 
		@JoinColumn( name = "MEMBER_ID")
	)
	private List<Address> addressHistory = new ArrayList<Address>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Period getWorkPeriod() {
		return workPeriod;
	}
	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}
	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}
	public List<Address> getAddressHistory() {
		return addressHistory;
	}
	public void setAddressHistory(List<Address> addressHistory) {
		this.addressHistory = addressHistory;
	}
	
//	@ManyToOne()
//	@JoinColumn(name = "TEAM_ID")
//	private Team team;
//	
//	public void setTeam(Team team) {
//		this.team = team;
//	}
//	
//	public Team getTeam() {
//		return team;
//	}
//	
////	@Column(name = "TEAM_ID")
////	private Long teamId;
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

//	public Long getTeamId() {
//		return teamId;
//	}
//
//	public void setTeamId(Long teamId) {
//		this.teamId = teamId;
//	}

	
	
}
