package fr.formation.spring.museum.controllers.jsp;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController
{
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)   
			new SecurityContextLogoutHandler().logout(request, response, auth);
		
		// You can redirect wherever you want, but generally it's a good practice
		// to show login screen again.
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
}
