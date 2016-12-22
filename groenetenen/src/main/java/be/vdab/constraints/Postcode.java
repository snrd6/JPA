package be.vdab.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;

import org.hibernate.validator.constraints.Range;

//Je definieert met @Target voor welke source onderdelen je de annotation kan tikken.
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.ANNOTATION_TYPE})
//Je definieert met @Retention hoe lang Java de annotation behoudt
@Retention(RetentionPolicy.RUNTIME)
//Je definieert in de huidige class enkel de annotation @Postcode. Je definieert in een aparte
//class (PostcodeValidator) de validatie code (ligt de waarde tussen 1000 en 9999 ?).
@Constraint(validatedBy={})
@Range(min = 1000, max = 9999)
public @interface Postcode {

@OverridesAttribute(constraint = Range.class, name = "message")
String message() default "{be.vdab.constraints.VanKleinerDanOfGelijkAanTot}";
//Elke validation annotation moet ook een optionele parameter groups hebben
Class<?>[]groups()default{};
//Elke validation annotation moet ook een optionele parameter payload hebben.
Class<? extends Payload>[]payload()default{};
	
}
