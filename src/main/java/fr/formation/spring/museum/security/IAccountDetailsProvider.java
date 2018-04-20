package fr.formation.spring.museum.security;

import fr.formation.spring.museum.models.Account;

public interface IAccountDetailsProvider {
	public AccountDetails getDetails();
	
	public Account getAccount();
}
