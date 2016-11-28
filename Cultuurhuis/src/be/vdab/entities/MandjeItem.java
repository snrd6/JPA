package be.vdab.entities;

import java.io.Serializable;

public class MandjeItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variabelen
	private Voorstelling voorstelling;
	private int plaatsen;
	
	//constructor
	public MandjeItem(Voorstelling voorstelling, int plaatsen) 
	{
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
	}
	
	//getters en setters
	
	public Voorstelling getVoorstelling() 
	{
		return voorstelling;
	}
	
	public void setVoorstelling(Voorstelling voorstelling) 
	{
		this.voorstelling = voorstelling;
	}
	
	public int getPlaatsen() 
	{
		return plaatsen;
	}
	
	public void setPlaatsen(int plaatsen) 
	{
		this.plaatsen = plaatsen;
	}
//hashcode en equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + plaatsen;
		result = prime * result + ((voorstelling == null) ? 0 : voorstelling.hashCode());
		return result;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MandjeItem))
			return false;
		MandjeItem other = (MandjeItem) obj;
		if (plaatsen != other.plaatsen)
			return false;
		if (voorstelling == null) {
			if (other.voorstelling != null)
				return false;
		} else if (!voorstelling.equals(other.voorstelling))
			return false;
		return true;
	}
	
	//toString
	@Override
	public String toString() {
		return "MandjeItem [voorstelling=" + voorstelling + ", plaatsen=" + plaatsen + "]";
	}
	
}
