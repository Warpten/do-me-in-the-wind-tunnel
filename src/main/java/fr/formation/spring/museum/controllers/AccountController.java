package fr.formation.spring.museum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.spring.museum.repositories.AccountRepository;

@Controller
@RequestMapping(path = "/user")
public class AccountController {
	
	@GetMapping("/landing")
	public @ResponseBody String landingLogin() {
		return "You're logged in";
	}
	
	@Autowired
	private AccountRepository accountRepository;
	
//	@RequestMapping(value = "/registration", method = RequestMethod.GET)
//	public String showRegistrationForm(WebRequest request, Model model) {
//	    Account userDto = new Account();
//	    model.addAttribute("user", userDto);
//	    return "registration.model";
//	}
	
	// @RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid Account accountEntity, 
//	      BindingResult result, WebRequest request, Errors errors) throws Exception {
//
//	    if (!result.hasErrors())
//	    {
//	    	if (accountRepository.findByUsername(accountEntity.getUsername()) != null)
//	    		throw new Exception("Account with same name already exists");
//	    	
//	    	accountEntity = accountRepository.save(accountEntity);
//	    }
//
//	    if (accountEntity == null)
//	        result.rejectValue("email", "message.regError");
//	    
//	    if (result.hasErrors())
//	    	return new ModelAndView("registration.model", "user", accountEntity);
//	    
//	    return new ModelAndView("registration.success.model", "user", accountEntity);
//	}
}
