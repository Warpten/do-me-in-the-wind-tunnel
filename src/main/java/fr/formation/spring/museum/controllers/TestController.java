package fr.formation.spring.museum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.repositories.AccountRepository;
import fr.formation.spring.museum.repositories.RankRepository;
import fr.formation.spring.museum.validators.AccountValidator;

@Controller
public class TestController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RankRepository rankRepository;
	
	@Autowired
	private AccountValidator accountValidator;
	
	// @PostConstruct -- Called after DI
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
		binder.addValidators(accountValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showHome() {
		ModelAndView model = new ModelAndView("jsp/test");
		model.addObject("account", new Account());
		model.addObject("rankList", rankRepository.findAll());
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView handlePost(@ModelAttribute @Validated Account modelAttribute,
			BindingResult result, WebRequest request, Errors errors) {

		if (accountRepository.findByUsername(modelAttribute.getUsername()) == null) {
			if (!result.hasErrors())
				accountRepository.save(modelAttribute);
		} else {
			errors.rejectValue("username", "error.username.already.used");
		}

		ModelAndView model = new ModelAndView("jsp/test");
		model.addObject("account", modelAttribute);
		model.addObject("rankList", rankRepository.findAll());
		return model;
	}
}