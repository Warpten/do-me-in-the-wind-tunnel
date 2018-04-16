package fr.formation.spring.museum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.repositories.AccountRepository;

@Controller
@RequestMapping(path = "/user")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    Account userDto = new Account();
	    model.addAttribute("user", userDto);
	    return "registration.model";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid Account accountEntity, 
	      BindingResult result, WebRequest request, Errors errors) throws Exception {

	    if (!result.hasErrors())
	    {
	    	if (accountRepository.findByUsername(accountEntity.getUsername()) != null)
	    		throw new Exception("Account with same name already exists");
	    	
	    	accountEntity = accountRepository.save(accountEntity);
	    }

	    if (accountEntity == null)
	        result.rejectValue("email", "message.regError");
	    
	    if (result.hasErrors())
	    	return new ModelAndView("registration.model", "user", accountEntity);
	    
	    return new ModelAndView("registration.success.model", "user", accountEntity);
	}
}
