package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Country;
import be.vdab.entities.Customer;

@Embeddable
public class Adres implements Serializable{
	//variabelen
	private static final long serialVersionUID = 1L;
	private String StreetAndNumber;
	private String city;
	private String state;
	private int postalCode;
	
	//associaties en relaties
	
	
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "countryId")
		private Country country;
		
	//constructor
	public Adres(String streetAndNumber, String city, String state, int postalCode) {
	
		this.StreetAndNumber = streetAndNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country=country;
		
	}
	
	protected Adres(){}



	
	
	//hash & equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StreetAndNumber == null) ? 0 : StreetAndNumber.hashCode());
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
		if (StreetAndNumber == null) {
			if (other.StreetAndNumber != null)
				return false;
		} else if (!StreetAndNumber.equals(other.StreetAndNumber))
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
		return "Adres [StreetAndNumber=" + StreetAndNumber + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", country=" + country + "]";
	}
	
	
	
	
	
	
	
	
	
}
