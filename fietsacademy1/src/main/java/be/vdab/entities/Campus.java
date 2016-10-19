package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table(name="campussen")
public class Campus implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public Campus(String naam, Adres adres) {
	setNaam(naam);
	setAdres(adres);
	telefoonNrs = new LinkedHashSet<>();
	docenten = new LinkedHashSet<>();
	}
	protected Campus() {}
	
	

	
	
	@ElementCollection 
	@CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusid")) 
	@OrderBy("fax") 
	private Set<TelefoonNr> telefoonNrs;
	
	
	public Set<TelefoonNr> getTelefoonNrs() {
		return Collections.unmodifiableSet(telefoonNrs);
		}
	
	public void add(TelefoonNr telefoonNr) {
		telefoonNrs.add(telefoonNr);
		}
	
	public void remove(TelefoonNr telefoonNr) {
		telefoonNrs.remove(telefoonNr);
		}
	
	//one to many
	@OneToMany(mappedBy="campus") 
	@OrderBy("voornaam, familienaam") 
	private Set<Docent> docenten;
	public Set<Docent> getDocenten() {
	return Collections.unmodifiableSet(docenten);
	}
	public void add(Docent docent) {
	docenten.add(docent);
	if (docent.getCampus() != this) { // als de andere kant nog niet bijgewerkt is bijwerken
		docent.setCampus(this);
	
	}
	}
	public void remove(Docent docent) {
	docenten.remove(docent);
	if (docent.getCampus() == this) { // als de andere kant nog niet bijgewerkt is bijwerken
		docent.setCampus(null);
	
}}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "managerid") 
	private Manager manager;
	public Manager getManager() {
	return manager;
}
}
