package be.vdab.web;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	
private final WerknemerService werknemerService;

private static final String WERKNEMERS_VIEW="werknemers/werknemers";

WerknemerController(WerknemerService werknemerService){
	this.werknemerService=werknemerService;
}

@GetMapping
ModelAndView findAll(Pageable pageable) {
return new ModelAndView(WERKNEMERS_VIEW,
"page", werknemerService.findAll(pageable));
}
}
