package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Product;


@Embeddable
public class Orderdetail implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private long quantityOrdered;
	private BigDecimal priceEach;

	
	
	//relaties en associaties
	@ManyToOne()
	@JoinColumn(name = "productId")
	private Product product;

	
	
	
	// constructor
	protected Orderdetail() {
	}

	public Orderdetail(long quantityOrdered, BigDecimal priceEach, Product product) throws IllegalArgumentException, NullPointerException {
		setQuantityOrdered(quantityOrdered);
		setPriceEach(priceEach);
		setProduct(product);
	}

	// getters

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public Product getProduct() {
		return product;
	}

	public BigDecimal getTotalValue() {
		return priceEach.multiply(BigDecimal.valueOf(quantityOrdered));
	}

	public void setQuantityOrdered(long quantityOrdered)  {
		this.quantityOrdered = quantityOrdered;
		}
	

	public void setPriceEach(BigDecimal priceEach)  {
		this.priceEach = priceEach;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	
	
	
	
	
	// hash & equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priceEach == null) ? 0 : priceEach.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + (int) (quantityOrdered ^ (quantityOrdered >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Orderdetail))
			return false;
		Orderdetail other = (Orderdetail) obj;
		if (priceEach == null) {
			if (other.priceEach != null)
				return false;
		} else if (!priceEach.equals(other.priceEach))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantityOrdered != other.quantityOrdered)
			return false;
		return true;
	}
	
	
	//tostring

	@Override
	public String toString() {
		return "Orderdetail [quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach + ", product=" + product
				+ "]";
	}

}

