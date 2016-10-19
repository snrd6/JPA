package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Geslacht;

@Entity
@Table(name = "docenten")
@NamedEntityGraph(name = Docent.MET_CAMPUS, attributeNodes = @NamedAttributeNode("campus"))	
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MET_CAMPUS = "Docent.metCampus";
	
	@Version
	private long versie;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;
	@Enumerated(EnumType.STRING)
	private Geslacht geslacht;

	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht, long rijksRegisterNr) {

		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		setRijksRegisterNr(rijksRegisterNr);
		bijnamen = new HashSet<>();
		verantwoordelijkheden = new LinkedHashSet<>();
	}

	protected Docent() {
	}// default constructor is vereiste voor JPA

	public static boolean isVoornaamValid(String voornaam) {
		return voornaam != null && !voornaam.isEmpty();
	}

	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && !familienaam.isEmpty();
	}

	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
	}

	public static boolean isRijksRegisterNrValid(long rijksRegisterNr) {
		long getal = rijksRegisterNr / 100;
		if (rijksRegisterNr / 1_000_000_000 < 50) {
			getal += 2_000_000_000;
		}
		return rijksRegisterNr % 100 == 97 - getal % 97;
	}

	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}

	// getter voor verzameling

	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen);
	}

	public void setVoornaam(String voornaam) {
		if (!isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		if (!isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}

	public void setWedde(BigDecimal wedde) {
		if (!isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public void setRijksRegisterNr(long rijksRegisterNr) {
		if (!isRijksRegisterNrValid(rijksRegisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksRegisterNr;
	}

	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}

	// verzameling bijnamen in een set

	@ElementCollection
	@CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentid"))
	@Column(name = "Bijnaam")
	private Set<String> bijnamen;

	// methods voor verzameling
	public void addBijnaam(String bijnaam) {
		bijnamen.add(bijnaam);
	}

	public void removeBijnaam(String bijnaam) {
		bijnamen.remove(bijnaam);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksRegisterNr ^ (rijksRegisterNr >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Docent))
			return false;
		Docent other = (Docent) obj;
		if (rijksRegisterNr != other.rijksRegisterNr)
			return false;
		return true;
	}

	// manyToOne
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "campusid")
	private Campus campus;

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		if (this.campus != null && this.campus.getDocenten().contains(this)) {
			// als de andere kant nog niet bijgewerkt is
			this.campus.remove(this); // werk je de andere kant bij
		}
		this.campus = campus;
		if (campus != null && !campus.getDocenten().contains(this)) {
			// als de andere kant nog niet bijgewerkt is
			campus.add(this); // werk je de andere kant bij
		}
	}

	@ManyToMany
	@JoinTable(
	name = "docentenverantwoordelijkheden",
	joinColumns = @JoinColumn(name="docentId"),
	inverseJoinColumns = @JoinColumn(name="verantwoordelijkheidId"))
			private Set<Verantwoordelijkheid> verantwoordelijkheden
			= new LinkedHashSet<>();
			public void add(Verantwoordelijkheid verantwoordelijkheid) {
			verantwoordelijkheden.add(verantwoordelijkheid);
			if ( ! verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.add(this);
			}
			}
			public void remove(Verantwoordelijkheid verantwoordelijkheid) {
			verantwoordelijkheden.remove(verantwoordelijkheid);
			if (verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.remove(this);
			}
			}
			public Set<Verantwoordelijkheid> getVerantwoordelijkheden() {
			return Collections.unmodifiableSet(verantwoordelijkheden);
			}
			
			
			
			
			

}
