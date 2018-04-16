package fr.formation.spring.museum.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.models.views.AccountModelView;
import fr.formation.spring.museum.repositories.AccountRepository;

@Controller
@RequestMapping(path = "/accounts")
public class JsonAccountController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping(path = "/enumerate/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(AccountModelView.Public.class)
	public @ResponseBody Iterable<Account> getAllAccountsJSON() {
		return accountRepository.findAll();
	}
	
	@GetMapping(path = "/enumerate/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Iterable<Account> getAllAccountsXML() {
		return accountRepository.findAll();
	}
	
	@GetMapping(path = "/details/{user}/json")
	@JsonView(AccountModelView.Public.class)
	public @ResponseBody Account getAccountDetailsJSON(
			@PathVariable(name = "user") String userName) {
		return accountRepository.findByUsername(userName);
	}
	
	@GetMapping(path = "/details/{user}/xml", produces = MediaType.APPLICATION_XML_VALUE)
	@JsonView(AccountModelView.Public.class)
	public @ResponseBody Account getAccountDetailsXML(
			@PathVariable(name = "user") String userName) {
		return accountRepository.findByUsername(userName);
	}
}
