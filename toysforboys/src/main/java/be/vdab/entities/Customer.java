package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

import be.vdab.valueobjects.Adres;


//entity gegenereerd
@Entity
@Table(name="customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
//variabelen
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	

	private String name;

	@Embedded
	private Adres adres;
	
	@Version
	private long version;


	//constructor
	
	protected Customer() {
	}
	public Customer(String name,Adres adres){
		this.name=name;
		this.adres=adres;
	}
	
	//getters
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Adres getAdres() {
		return adres;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	
	//hash & equals
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
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
		return "Customer [name=" + name + ", adres=" + adres + "]";
	}
	
	
	
	
	
	
	


}