package fr.formation.spring.museum.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.models.Rank;

public class AccountDetails implements UserDetails {

	private static final long serialVersionUID = 2411138079737409108L;

	private Account account;

	public AccountDetails(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Rank rank : account.getRanks())
			authorities.add(new SimpleGrantedAuthority("ROLE_" + rank.getAuthority()));
		return authorities;
	}
	
	public boolean hasAuthority(String authority) {
		for (Rank rank : account.getRanks())
			if (rank.getAuthority().equals(authority))
				return true;
		return false;
	}

	@Override
	public String getPassword() { return account.getPassword(); }

	@Override
	public String getUsername() { return account.getUsername(); }

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }
	
	public Account getAccount() { return this.account; }
}
