package testone;

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
import javax.persistence.OneToMany;

@Entity
public class Member extends BasicClass {
	
	@Id @GeneratedValue
	@Column( name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	@Embedded
	private Address address;

	@OneToMany( mappedBy = "member")
 	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable( name = "FAVORITE_FOOD",
	 joinColumns = @JoinColumn( name = "MEMBER_ID")
	)
	@Column( name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();
	
	@ElementCollection
	@CollectionTable( name = "ADDRESS",
	 joinColumns = @JoinColumn( name = "MEMBER_ID")
	)
	private List<Address> addressHistory = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
}
