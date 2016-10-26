package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;



@Entity
@Table(name="countries")
public class Country implements Serializable {
	//variabelen
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String name;

	@Version
	private long version;
	
	//constructors
	protected Country() {
	}
	
	public Country(String name) throws IllegalArgumentException{
		this.name=name;
	}
	
	
	//getters

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	

	
	//hashcode en equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Country))
			return false;
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	//tostring
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
	
}
