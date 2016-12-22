package be.vdab.web;

import com.sun.istack.NotNull;

import be.vdab.entities.Filiaal;

class AfschrijvenForm {
	
	@NotNull
	private Filiaal filiaal;

	public Filiaal getFiliaal() {
		return filiaal;
	}

	public void setFiliaal(Filiaal filiaal) {
		this.filiaal = filiaal;
	}
	

}
