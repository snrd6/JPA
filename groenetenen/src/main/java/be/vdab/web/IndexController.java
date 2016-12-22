package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
 class IndexController {
	private static final String VIEW="index";
	private final Voorkeur voorkeur;
	
	@GetMapping
	ModelAndView index(){
		return new ModelAndView(VIEW,"foto",voorkeur.getFoto());
	}
	 IndexController(Voorkeur voorkeur) {
		this.voorkeur=voorkeur;
	}
	 
	 @GetMapping(params="foto")
	 String kleurKeuze(String foto){
		 voorkeur.setFoto(foto);
		 return "redirect:/";
	 }

}
