package be.vdab.entities;

import java.io.Serializable;

public class Klant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//variabelen
	private long klantId;	
	private String voornaam;
	private String familienaam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	private String gebruikersnaam;
	private String paswoord;
	
	//constructor
	public Klant(String voornaam, String familienaam, String straat, String huisNr, String postCode,
			String gemeente, String gebruikersnaam, String paswoord) {
		
		
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postCode;
		this.gemeente = gemeente;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}
	public Klant(long klantId,String voornaam, String familienaam, String straat, String huisNr, String postCode,
			String gemeente, String gebruikersnaam, String paswoord) {
		
		
		this.klantId=klantId;
	}

	//getters
	public long getKlantId() {
		return klantId;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getPaswoord() {
		return paswoord;
	}
	
	//setters

	public void setKlantId(long klantId) {
		this.klantId = klantId;
	}

	public void setVoornaam(String voornaam) {
		if((voornaam == null) || voornaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		if((familienaam == null) || familienaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}

	public void setStraat(String straat) {
		if ( (straat == null) || straat.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.straat = straat;
	}

	public void setHuisNr(String huisNr) {
		if((huisNr == null) || huisNr.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.huisNr = huisNr;
	}

	public void setPostCode(String postCode) {
		if((postCode == null) || postCode.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.postCode = postCode;
	}

	public void setGemeente(String gemeente) {
		if((gemeente == null) || gemeente.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.gemeente = gemeente;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		if((gebruikersnaam == null) || gebruikersnaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	public void setPaswoord(String paswoord) {
		if((paswoord == null) || paswoord.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.paswoord = paswoord;
	}
	
	//hashcode en equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familienaam == null) ? 0 : familienaam.hashCode());
		result = prime * result + ((gebruikersnaam == null) ? 0 : gebruikersnaam.hashCode());
		result = prime * result + ((gemeente == null) ? 0 : gemeente.hashCode());
		result = prime * result + ((huisNr == null) ? 0 : huisNr.hashCode());
		result = prime * result + (int) (klantId ^ (klantId >>> 32));
		result = prime * result + ((paswoord == null) ? 0 : paswoord.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Klant))
			return false;
		Klant other = (Klant) obj;
		if (familienaam == null) {
			if (other.familienaam != null)
				return false;
		} else if (!familienaam.equals(other.familienaam))
			return false;
		if (gebruikersnaam == null) {
			if (other.gebruikersnaam != null)
				return false;
		} else if (!gebruikersnaam.equals(other.gebruikersnaam))
			return false;
		if (gemeente == null) {
			if (other.gemeente != null)
				return false;
		} else if (!gemeente.equals(other.gemeente))
			return false;
		if (huisNr == null) {
			if (other.huisNr != null)
				return false;
		} else if (!huisNr.equals(other.huisNr))
			return false;
		if (klantId != other.klantId)
			return false;
		if (paswoord == null) {
			if (other.paswoord != null)
				return false;
		} else if (!paswoord.equals(other.paswoord))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}
	

	
	
	
}
