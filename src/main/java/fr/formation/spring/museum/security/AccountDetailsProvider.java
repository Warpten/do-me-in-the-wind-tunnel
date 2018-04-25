package fr.formation.spring.museum.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.formation.spring.museum.models.Account;

@Component
public class AccountDetailsProvider implements IAccountDetailsProvider {
	public AccountDetails getDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof AccountDetails))
			return null;
		return (AccountDetails)principal;
	}
	
	public Account getAccount() {
		AccountDetails details = getDetails();
		if (details == null)
			return null;
		
		return details.getAccount();
	}
}