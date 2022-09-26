package hellojpa;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	public Address() {}
	
	public Address(String city , String street , String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street , zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) { 
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Address address = (Address) obj;
		return Objects.equals(city, address.city) &&
				Objects.equals(street, address.street) &&
				Objects.equals(zipcode, address.zipcode);
	}
	
}
