package be.vdab.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.valueobjects.PostcodeReeks;
//@ PostcodeReeksVanKleinerDanOfGelijkAanTot mag voorkomen bij de class PostcodeReeks.
public class PostcodeReeksVanKleinerDanOfGelijkAanTotValidator implements ConstraintValidator<PostcodeReeksVanKleinerDanOfGelijkAanTot, PostcodeReeks>{
	
	
	@Override
	public void initialize(PostcodeReeksVanKleinerDanOfGelijkAanTot arg0){}
	
	//Bean validation roept de method isValid op bij het valideren van een PostcodeReeks
	//object. Je geeft true terug als de variabele een correcte waarde bevat.
	@Override
	public boolean isValid(PostcodeReeks reeks,ConstraintValidatorContext context){
		//Zoals de ingebakken annotations (@Min, @Max, ...) produceer je geen foutmelding als de te valideren waarden gelijk zijn aan null.
		if (reeks.getVanpostcode() == null || reeks.getTotpostcode() == null) { 
			return true;
			}
		//De validatie is correct als vanpostcode kleiner is of gelijk aan totpostcode
			return reeks.getVanpostcode() <= reeks.getTotpostcode();
	}

}
