package be.vdab.entities;

import java.io.Serializable;

public class Reservatie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//variabelen
	private long reservatieId;
	private long klantId;
	private long voorstellingsId;
	private int plaaten;
	
	//constructor
	public Reservatie(long reservatieId, long klantId, long voorstellingsId, int plaaten) {
	
		this.reservatieId = reservatieId;
		this.klantId = klantId;
		this.voorstellingsId = voorstellingsId;
		this.plaaten = plaaten;
	}
	
	
	//getters en setters

	public long getReservatieId() {
		return reservatieId;
	}

	public void setReservatieId(long reservatieId) {
		this.reservatieId = reservatieId;
	}

	public long getKlantId() {
		return klantId;
	}

	public void setKlantId(long klantId) {
		this.klantId = klantId;
	}

	public long getVoorstellingsId() {
		return voorstellingsId;
	}

	public void setVoorstellingsId(long voorstellingsId) {
		this.voorstellingsId = voorstellingsId;
	}

	public int getPlaaten() {
		return plaaten;
	}

	public void setPlaaten(int plaaten) {
		this.plaaten = plaaten;
	}
//hashcode en equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (klantId ^ (klantId >>> 32));
		result = prime * result + plaaten;
		result = prime * result + (int) (reservatieId ^ (reservatieId >>> 32));
		result = prime * result + (int) (voorstellingsId ^ (voorstellingsId >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reservatie))
			return false;
		Reservatie other = (Reservatie) obj;
		if (klantId != other.klantId)
			return false;
		if (plaaten != other.plaaten)
			return false;
		if (reservatieId != other.reservatieId)
			return false;
		if (voorstellingsId != other.voorstellingsId)
			return false;
		return true;
	}


	
	//toString
	@Override
	public String toString() {
		return "Reservatie [reservatieId=" + reservatieId + ", klantId=" + klantId + ", voorstellingsId="
				+ voorstellingsId + ", plaaten=" + plaaten + "]";
	}
	
	

	
	
}
