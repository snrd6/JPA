package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;


//gegenereerd
@Entity
@Table(name="productlines")
@NamedQuery(name="Productline.findAll", query="SELECT p FROM Productline p")
public class Productline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String name;

	private int version;

	public Productline() {
	}

	public int getId() {
		return this.id;
	}



	public String getDescription() {
		return this.description;
	}



	public String getName() {
		return this.name;
	}



	public int getVersion() {
		return this.version;
	}


}