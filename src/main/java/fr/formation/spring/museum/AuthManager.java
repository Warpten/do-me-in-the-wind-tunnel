//package fr.formation.spring.museum;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//import fr.formation.spring.museum.models.Account;
//import fr.formation.spring.museum.repositories.AccountRepository;
//
//public class AuthManager implements AuthenticationManager {
//	protected final Log logger = LogFactory.getLog(getClass());
//
//	@Autowired
//	private AccountRepository accountRepository;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		
//		Account user = accountRepository.findByUsername(username);
//		if (user == null)
//			throw new BadCredentialsException("1000");
//		
//		if (!user.getPassword().equals(password))
//			throw new BadCredentialsException("1001");
//		
//		logger.info("User '" + username + "' logged in.");
//		
//		return new UsernamePasswordAuthenticationToken(username, password);
//	}
//
//}
