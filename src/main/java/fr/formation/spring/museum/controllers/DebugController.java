package fr.formation.spring.museum.controllers;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/debug")
public class DebugController {
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/hellotest")
	public ModelAndView handleRequest(@RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("Saying hello to '" + name + "'.");

		ModelAndView model = new ModelAndView("helloWorld", "name", name);
		model.addObject("message", new Date().toString());
		return model;
	}

	@GetMapping("/hellotest2")
	public String handleRequest2(ModelMap model, @RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("Saying hello to '" + name + "'.");

		model.addAttribute("name", name);
		return "helloWorld";
	}
	
	@GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}
