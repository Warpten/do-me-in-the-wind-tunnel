//package fr.formation.spring.museum;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	
//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return new AuthManager();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/user/login*").anonymous()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.loginPage("/user/login.html")
//			.and()
//			.logout().logoutSuccessUrl("/user/login.html");
//	}
//}
