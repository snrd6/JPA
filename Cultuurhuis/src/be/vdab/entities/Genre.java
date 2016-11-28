package be.vdab.entities;

public class Genre {
	
	//variabelen
	private long IDGenre;
	private String naam;
	
	
	//constructor
	public Genre(long IDGenre,String naam){
		this.IDGenre=IDGenre;
		this.naam=naam;
	}

		
	//getters 
	public long getIDGenre() {
		return IDGenre;
	}

	public String getNaam() {
		return naam;
	}
	
	//hashcode en equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (IDGenre ^ (IDGenre >>> 32));
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Genre))
			return false;
		Genre other = (Genre) obj;
		if (IDGenre != other.IDGenre)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

//toString
	@Override
	public String toString() {
		return "Genre [IDGenre=" + IDGenre + ", naam=" + naam + "]";
	}
	
	

}
