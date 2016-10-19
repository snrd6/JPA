package be.vdab.entities;

import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
@Table(name ="cursussen")
public abstract class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String naam;
	
	@Override
	public String toString() {
	return naam;
	}
}
