package fr.formation.spring.museum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.repositories.AccountRepository;

/**
 * A service that interfaces between {@link JpaUserDetailsService} and {@link AccountDetails}
 * for use by Spring Security.
 * 
 * @author INTI0356
 *
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = accountRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("No existing account found for username '" + username + "'.");

		if (!user.isEnabled())
			throw new UsernameNotFoundException("shitters clogged");
		
		return new AccountDetails(user);

	}
}
