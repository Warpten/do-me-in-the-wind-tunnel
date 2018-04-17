package fr.formation.spring.museum.controllers.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.spring.museum.repositories.AccountRepository;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	private  String dateFormat = "yyyy-MM-dd";
	
	@GetMapping(value = "/management")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView handleUserManagement() {
		
		ModelAndView mv = new ModelAndView("jsp/user/mgmt");
		mv.addObject("users", accountRepository.findAll());
		mv.addObject("dateFormat", dateFormat);
		// ...
		
		return mv;
	}
}
