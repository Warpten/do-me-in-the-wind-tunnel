package fr.formation.spring.museum.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.formation.spring.museum.models.Account;

@Component
public class AccountDetailsProvider implements IAccountDetailsProvider {
	public AccountDetails getDetails() {
		return (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public Account getAccount() { return getDetails().getAccount(); }
}