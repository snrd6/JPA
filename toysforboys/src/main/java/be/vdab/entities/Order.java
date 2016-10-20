package be.vdab.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable{
	
	//variabelen
	
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date requiredDate;
	private long customerId;
	private String comments;
	
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
	public long getCustomerId() {
		return customerId;
	}
	public String getComments() {
		return comments;
	}
	
	//constructor
	public Order( Date orderDate, Date requiredDate, String comments, Customer customer) throws NullPointerException {
	
		
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.comments = comments;
		this.customer = customer;
	}
	protected Order(){}
	
	
	
	
	

	
	

}
