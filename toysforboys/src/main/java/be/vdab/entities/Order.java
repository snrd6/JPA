package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import be.vdab.exceptions.UnshippedException;
import be.vdab.valueobjects.Orderdetail;
import be.vdab.enums.Status;

//entity gegenereerd
@Entity
@Table(name = "orders")
@NamedEntityGraph(name = "Order.metCustomer", attributeNodes = @NamedAttributeNode("customer"))	
public class Order implements Serializable{
	
	//variabelen
	
	
	private static final long serialVersionUID = 1L;
	public static final String MET_CUSTOMER="Order.metCustomer";
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date requiredDate;
	@Temporal(TemporalType.DATE)
	private Date shippedDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Version
	private long version;
	
	

	
	private String comments;

	
	@ElementCollection
	@CollectionTable(name="orderdetails",joinColumns=@JoinColumn(name="orderId"))
	private Set<Orderdetail>orderdetails;
	
	
	
	//associaties en relaties
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
	//Getters
	
	public long getId() {
		return id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public Date getShippedDate() {
		return requiredDate;
	}
	public Status getStatus(){
		return status;
	}

	public String getComments() {
		return comments;
	}
	
	public Customer getCustomer() {
		return customer;
	}
//setters
	
	public void setId(long id) {
		this.id = id;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	
	
	public void setStatus(Status status){
		this.status=status;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	//constructor
	public Order( Date orderDate, Date requiredDate,Date shippedDate, String comments, Customer customer,Status status) throws NullPointerException {
	
		
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate=shippedDate;
		this.comments = comments;
		this.customer = customer;
		this.status=status;
	}
	protected Order(){}
	
	//methods
	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

	

	public BigDecimal getTotalValue() {
		BigDecimal totalValue = BigDecimal.ZERO;
		for (Orderdetail orderdetail : this.getOrderdetails()) {
			totalValue = totalValue.add(orderdetail.getTotalValue());
		}
		return totalValue;
	}

   
	public void ship() throws UnshippedException, IllegalArgumentException {
		for (Orderdetail orderdetail : this.getOrderdetails()) {
			orderdetail.getProduct().ship(orderdetail.getQuantityOrdered());
		}
		setStatus(Status.SHIPPED);
		shippedDate = new Date();
	}
	
	
	//hash & equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderdetails == null) ? 0 : orderdetails.hashCode());
		result = prime * result + ((requiredDate == null) ? 0 : requiredDate.hashCode());
		result = prime * result + ((shippedDate == null) ? 0 : shippedDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderdetails == null) {
			if (other.orderdetails != null)
				return false;
		} else if (!orderdetails.equals(other.orderdetails))
			return false;
		if (requiredDate == null) {
			if (other.requiredDate != null)
				return false;
		} else if (!requiredDate.equals(other.requiredDate))
			return false;
		if (shippedDate == null) {
			if (other.shippedDate != null)
				return false;
		} else if (!shippedDate.equals(other.shippedDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	//tostring
	@Override
	public String toString() {
		return "Order [orderDate=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate
				+ ", comments=" + comments + ", status=" + status + ", orderdetails=" + orderdetails + ", customer="
				+ customer + "]";
	}
	
	
	
	

	
	
	
	

}
