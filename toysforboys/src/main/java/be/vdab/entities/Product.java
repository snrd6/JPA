package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import java.util.logging.Logger;
import java.util.logging.Level;

import be.vdab.exceptions.UnshippedException;
import be.vdab.valueobjects.Productline;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	//variabelen
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name; 
	private String scale;
	private String description;
	private long quantityInStock;
	private long quantityInOrder;
	private BigDecimal buyPrice;
	
	@Version
	private long version;
	
	//relaties en associaties
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productlineId")
	private Productline productline;
	
	
	@Transient
	private final static Logger logger= Logger.getLogger(Product.class.getName());
	
	//constructor

	
	protected Product(){}

	
	
	public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
			BigDecimal buyPrice,Productline productline) {
	
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
		this.productline=productline;
	}



	//Getters
	public long getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	public String getScale(){
		return scale;
	}
	public String getDescription(){
		return description;
	}
	public long getQuantityInStock() {
		return quantityInStock;
	}
	public long getQuantityInOrder(){
		return quantityInOrder;
	}
	
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public Productline getProductline(){
		return productline;
	}
	
	//setters
	
	public void setId(long id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setScale(String scale) {
		this.scale = scale;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public void setQuantityInStock(long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}



	public void setQuantityInOrder(long quantityInOrder) {
		this.quantityInOrder = quantityInOrder;
	}



	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}



	public void setProductline(Productline productline) {
		this.productline = productline;
	}
	
	
	
	
	
	
	//hash & equals



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyPrice == null) ? 0 : buyPrice.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productline == null) ? 0 : productline.hashCode());
		result = prime * result + (int) (quantityInOrder ^ (quantityInOrder >>> 32));
		result = prime * result + (int) (quantityInStock ^ (quantityInStock >>> 32));
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productline == null) {
			if (other.productline != null)
				return false;
		} else if (!productline.equals(other.productline))
			return false;
		if (quantityInOrder != other.quantityInOrder)
			return false;
		if (quantityInStock != other.quantityInStock)
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
			return false;
		return true;
	}

	//tostring

	@Override
	public String toString() {
		return "Product [name=" + name + ", scale=" + scale + ", description=" + description + ", quantityInStock="
				+ quantityInStock + ", quantityInOrder=" + quantityInOrder + ", buyPrice=" + buyPrice + ", productline="
				+ productline + "]";
	}
	

	
	
	

	
	//methods
	

	public void ship(long quantityOrdered)throws UnshippedException ,IllegalArgumentException {
		if (quantityOrdered <= quantityInStock) {
			setQuantityInStock(quantityInStock - quantityOrdered);
			if (quantityOrdered <= quantityInOrder) {
				setQuantityInOrder(quantityInOrder - quantityOrdered);
			} else {
				
				logger.log(Level.WARNING, "Corrupted quantityInOrder for " + this.getName());
			}
		} else {
			throw new UnshippedException();
		}
	}
	
}
