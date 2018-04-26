package fr.formation.spring.museum.controllers.jsp;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.models.RegistrationDTO;
import fr.formation.spring.museum.repositories.AccountRepository;
import fr.formation.spring.museum.repositories.RankRepository;

@Controller
public class SecurityController
{
	private @Autowired AccountRepository accountRepository;
	private @Autowired RankRepository rankRepository;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)   
			new SecurityContextLogoutHandler().logout(request, response, auth);
		
		return "redirect:/login?logout";
	}
	
	/***
	 * Both of these can be handled by adding similar code
	 * to this to WebMvcConfigurerAdapter suclasses:
	 * 
	 * 	@Override
	 * public void addViewControllers(ViewControllerRegistry registry) {
	 *     registry.addViewController("/403").setViewName("jsp/403");
	 * }
	 * 
	 */
	
	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
			model.addAttribute("message", "label.form.login.error.already_logged_in");
		return "jsp/login";
	}
	
	@GetMapping(value = "/register")
	public String registerPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
			model.addAttribute("message", "label.form.login.error.already_logged_in");
		model.addAttribute("registrationModel", new RegistrationDTO());
		return "jsp/register";
	}
	
	@PostMapping(value = "/register")
	public String registerPagePOST(Model model, @ModelAttribute @Validated RegistrationDTO dto,
			BindingResult result) {
		
		model.addAttribute("registrationModel", dto);
		
		if (accountRepository.existsByUsername(dto.getUsername())) {
			model.addAttribute("error_username", "error.form.register.username_taken");
			return "jsp/register";
		}
		
		if (accountRepository.existsByEmail(dto.getEmail())) {
			model.addAttribute("error_mail", "error.form.register.email_used");
			return "jsp/register";
		}
		
		if (!result.hasErrors()) {
			String encryptedPassword = passwordEncoder.encode(dto.getPassword());
			Account newAccount = new Account();
			newAccount.setPassword(encryptedPassword);
			newAccount.setUsername(dto.getUsername());
			newAccount.setName(dto.getName());
			newAccount.setSurname(dto.getSurname());
			newAccount.setRegistrationDate(Date.from(Instant.now()));
			newAccount.setEnabled(false);
			newAccount.setRank(rankRepository.findByName("USER"));
			newAccount.setEmail(dto.getEmail());
			accountRepository.save(newAccount);
			return "redirect:/login";
		} else {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (int i = 0; i < fieldErrors.size(); ++i)
			{
				System.out.println("error_" + fieldErrors.get(i).getField() + " = " + fieldErrors.get(i).getDefaultMessage());
				model.addAttribute("error_" + fieldErrors.get(i).getField(), fieldErrors.get(i).getDefaultMessage());
			}
		}
		
		return "jsp/register";
	}
	
	@GetMapping(value = "/403")
	public ModelAndView accessDeniedPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("jsp/403");
		mv.addObject("previousPage", getPreviousPageByRequest(request).orElse("/"));
		return mv;
	}
	
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
	{
	   return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
	}
	
	@ExceptionHandler({ UsernameNotFoundException.class })
	public ModelAndView usernameNotFound(Exception ex) {
		ModelAndView mv = new ModelAndView("/login?error");
		
		mv.addObject("error", "error_username_not_found");
		return mv;
	}
}
