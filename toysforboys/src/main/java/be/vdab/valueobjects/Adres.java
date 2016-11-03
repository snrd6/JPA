package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;





@Embeddable
public class Adres implements Serializable{
	//variabelen
	private static final long serialVersionUID = 1L;
	private String streetAndNumber;
	private String city;
	private String state;
	private int postalCode;
	

	//associaties en relaties
	
	
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "countryId")
		private Country country;
		
	//constructor
	public Adres(String streetAndNumber, String city, String state, int postalCode) {
	
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		
		
	}
	
	protected Adres(){}


//getters en setters
	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
	
	
	//hash & equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((streetAndNumber == null) ? 0 : streetAndNumber.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + postalCode;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Adres))
			return false;
		Adres other = (Adres) obj;
		if (streetAndNumber == null) {
			if (other.streetAndNumber != null)
				return false;
		} else if (!streetAndNumber.equals(other.streetAndNumber))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (postalCode != other.postalCode)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
	
	//tostring
	
	
	@Override
	public String toString() {
		return "Adres [streetAndNumber=" + streetAndNumber + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", country=" + country + "]";
	}
	
	
	
	
	
	
	
	
	
}
