package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	//variabelen
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name; 
	private int quantityInStock;
	private BigDecimal buyPrice;
	
	//constructor
	public Product(String name, int quantityInStock, BigDecimal buyPrice) {
		this.name = name;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
	}
	
	protected Product(){}

	
	
	//Getters
	public long getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getQuantityInStock() {
		return quantityInStock;
	}
	
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	//hash & equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyPrice == null) ? 0 : buyPrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantityInStock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (buyPrice == null) {
			if (other.buyPrice != null)
				return false;
		} else if (!buyPrice.equals(other.buyPrice))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantityInStock != other.quantityInStock)
			return false;
		return true;
	}

	
	//tostring
	@Override
	public String toString() {
		return "Product [name=" + name + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + "]";
	}

	
	

	
	
}
