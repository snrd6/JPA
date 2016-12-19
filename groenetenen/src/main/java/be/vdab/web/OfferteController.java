package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offertes")
class OfferteController {

	private static final String STAP1_VIEW="offertes/stap1";
	private static final String STAP2_VIEW="offertes/stap2";
	private static final String REDIRECT_URL_NA_TOEVOEGEN="redirect:/";
	private static final Logger LOGGER=Logger.getLogger(OfferteController.class.getName());
	
	
	//getmappings
	//Bij een get request naar /offertes/aanvraag toon je de eerste pagina
	@GetMapping("aanvraag")
	String createForm1(){
		return STAP1_VIEW;
	}
	
	
	
	
	//postmapping
	//Een klik op de knop "Volgende stap" stuurt een POST request met een parameter volgende(naam submitknop) naar /offertes/aanvragen
	@PostMapping(params="volgende")
	String createForm1Naar2(){
		return STAP2_VIEW;
	}
	
	//Een klik op de knop "Vorige stap" stuurt een POST request met een parameter vorige(naam submitknop) naar /offertes/aanvragen
	@PostMapping(params="vorige")
	String createForm2Naar1(){
		return STAP1_VIEW;
	}
	
	//Een klik op de knop "Bevestigen" stuurt een POST request met een parameter bevestigen(naam submitknop) naar /offertes/aanvragen
	@PostMapping(params="bevestigen")
	String create(){
		LOGGER.info("offerte versturen via e-mail");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
}
