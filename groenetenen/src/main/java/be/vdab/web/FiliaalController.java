package be.vdab.web;

import java.util.List;
import java.util.Optional;

import javax.enterprise.inject.New;
import javax.validation.Valid;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;

//class waarvoor je controller tikt is een controllerbean
@Controller
@RequestMapping("/filialen")
class FiliaalController {

	private static final String FILIALEN_VIEW="filialen/filialen";
	private static final String TOEVOEGEN_VIEW="filialen/toevoegen";
	
	private static final String REDIRECT_URL_NA_TOEVOEGEN="redirect:/filialen";

	
	private static final String FILIAAL_VIEW="filialen/filiaal";
	private final FiliaalService filiaalService;
	
	//aangemaakt met verwijderknop zie post
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN ="redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN ="redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS ="redirect:/filialen/{id}";
	private static final String VERWIJDERD_VIEW="filialen/verwijderd";
	
	
	//HTML FORM tonen aan de gebruiker
	private static final String PER_POSTCODE_VIEW="filialen/perpostcode";
	
	//wijzigknop
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";
	
	//aanpassing na version
	private static final String REDIRECT_URL_NA_LOCKING_EXCEPTION ="redirect:/filialen/{id}?optimisticlockingexception=true";
	
	
	//afschrijven waarde op 0 zetten
	private static final String AFSCHRIJVEN_VIEW="filialen/afschrijven";
	
	//gesubmitte pagina verwerken
	private static final String REDIRECT_NA_AFSCHRIJVEN = "redirect:/";
	
	FiliaalController(FiliaalService filiaalService){
		this.filiaalService=filiaalService;
	}
	

	//getMapping ---->
	
	
	@GetMapping
	ModelAndView findAll(){
		return new ModelAndView(FILIALEN_VIEW,"filialen", filiaalService.findAll()).addObject("aantalFilialen",filiaalService.findAantalFilialen());
	}
	
	@GetMapping("toevoegen")
	ModelAndView createForm(){
		return new ModelAndView(TOEVOEGEN_VIEW,"filiaal",new Filiaal());
		
	}
	
	@GetMapping("{id}")
	ModelAndView read(@PathVariable Filiaal filiaal){
		ModelAndView modelAndView=new ModelAndView(FILIAAL_VIEW);
		if(filiaal !=null){
			modelAndView.addObject(filiaal);
		}
	
	return modelAndView;
	}
	
	@GetMapping("{id}/verwijderd")
	String deleted(String naam){
		return VERWIJDERD_VIEW;
	}
	
	@GetMapping("perpostcode")
	ModelAndView findByPostcodeReeks(){
		PostcodeReeks reeks =new PostcodeReeks();
		//reeks.setVanpostcode(1000);
		//reeks.setTotpostcode(9999);
		return new ModelAndView(PER_POSTCODE_VIEW).addObject(reeks);
	}
	
	@GetMapping(params={"vanpostcode","totpostcode"})
	ModelAndView findByPostcodeReeks(@Valid PostcodeReeks reeks,BindingResult bindingResult){
		 ModelAndView modelAndView=new ModelAndView(PER_POSTCODE_VIEW);
		 if(!bindingResult.hasErrors()){
			 List <Filiaal> filialen=filiaalService.findByPostcodeReeks(reeks);
			 if(filialen.isEmpty()){
				 bindingResult.reject("geenFilialen");
			 }
			 else{
				 modelAndView.addObject("filialen",filiaalService.findByPostcodeReeks(reeks));
			 }
			
		 }
		 return modelAndView;
	}
	
	
	@GetMapping("afschrijven")
	ModelAndView AfschrijvenForm(){
		return new ModelAndView(AFSCHRIJVEN_VIEW,"filialen",filiaalService.findNietAfgeschreven()).addObject(new AfschrijvenForm());
	}
	//Postmapping ---->
	
	@PostMapping
	String create(@Valid Filiaal filiaal, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	return TOEVOEGEN_VIEW;
	}
	filiaalService.create(filiaal);
	return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
	@PostMapping("{filiaal}/verwijderen")
	//De @PostMapping method bevat een RedirectAttributes parameter.
	//Je kan daarmee in de URL van de redirect
	//a. request parameters toevoegen
	//b. path variabelen invullen
	String delete (@PathVariable Filiaal filiaal,RedirectAttributes redirectAttributes){
		
		long id=filiaal.getId();
		try{
			filiaalService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam", filiaal.getNaam()); 
			return REDIRECT_URL_NA_VERWIJDEREN; 
			} catch (FiliaalHeeftNogWerknemersException ex) {
			redirectAttributes.addAttribute("id", id)
			.addAttribute("fout", "Filiaal heeft nog werknemers");
			return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
			}
			
		}
	
	
	@InitBinder("postcodeReeks")
	void initBinderPostcodeReeks(WebDataBinder binder){
		binder.initDirectFieldAccess();
	}
	
	@InitBinder("filiaal")
	void initBinderFiliaal(WebDataBinder binder){
		binder.initDirectFieldAccess();
	}
	
	@GetMapping("{filiaal}/wijzigen")
	ModelAndView updateForm(@PathVariable Filiaal filiaal) {

	
	return new ModelAndView(WIJZIGEN_VIEW).addObject(filiaal);
	}
	
	@PostMapping("{id}/wijzigen")
	String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	return WIJZIGEN_VIEW;
	}
	
	

	try{
		filiaalService.update(filiaal);
		return REDIRECT_URL_NA_WIJZIGEN;
	}catch (ObjectOptimisticLockingFailureException ex){
		return REDIRECT_URL_NA_LOCKING_EXCEPTION;
	}
	}
	
	@PostMapping("afschrijven")
	ModelAndView afschrijven(@Valid AfschrijvenForm afschrijvenForm,
	BindingResult bindingResult) {
	if (bindingResult.hasErrors()) { // als de gebruiker geen filiaal selecteerde
	return new ModelAndView(AFSCHRIJVEN_VIEW, "filialen",
	filiaalService.findNietAfgeschreven());
	}
	filiaalService.afschrijven(afschrijvenForm.getFiliaal());
	return new ModelAndView(REDIRECT_NA_AFSCHRIJVEN);
	}

	
}
