package fr.formation.spring.museum.controllers.jsp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.repositories.AccountRepository;
import fr.formation.spring.museum.security.IAccountDetailsProvider;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private IAccountDetailsProvider accountDetailsProvider;

	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	private  String dateFormat = "yyyy-MM-dd";

	@GetMapping(value = "/users")
	// @PreAuthorize("hasRole('ROLE_ADMIN')") // done with security
	public ModelAndView handleUserManagement(@RequestParam(value = "page", defaultValue = "0") int page) {
		
		return handleUserListingRequest(page);
	}
	
	private ModelAndView handleUserListingRequest(int page) {
		final int RESULT_PER_PAGE = 50;
		
		// If using page=...&size=..., Spring automatically creates a Pageable for us. But we don't want that.
		PageRequest pageable = PageRequest.of(page, RESULT_PER_PAGE);

		ModelAndView mv = new ModelAndView("jsp/admin/listing");
		mv.addObject("users", accountRepository.findAll(pageable).getContent());
		mv.addObject("dateFormat", dateFormat);
		mv.addObject("totalResults", accountRepository.count());
		mv.addObject("pageId", page);
		mv.addObject("resultPerPage", RESULT_PER_PAGE);

		// ...
		
		return mv;
	}
	
	@GetMapping(value = "/disable")
	public ModelAndView handleUserDisable(@RequestParam(value = "id", required = true) int accId) {
		String returnCode = toggleUserState(accId, false);
		
		ModelAndView mv = handleUserManagement(0);
		mv.addObject("error", returnCode);
		return mv;
	}
	
	private String toggleUserState(int accId, boolean enabled) {
		if (enabled == false && accountDetailsProvider.getAccount().getId() == accId)
			return "errors.admin.cant_disable_self";
		
		Optional<Account> account = accountRepository.findById(accId);
		if (account.isPresent()) {
			Account acc = account.get();

			acc.setEnabled(enabled);
			accountRepository.save(acc);
			
			return null;
		}
		
		return "errors.admin.disable.account_not_found";
	}
	
	@GetMapping(value = "/enable")
	public ModelAndView handleUserEnable(@RequestParam(value = "id", required = true) int accId) {
		String returnCode = toggleUserState(accId, true);
		
		ModelAndView mv = handleUserManagement(0);
		mv.addObject("error", returnCode);
		return mv;
	}
}
