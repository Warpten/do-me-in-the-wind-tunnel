package fr.formation.spring.museum.controllers.jsp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.models.AccountDTO;
import fr.formation.spring.museum.repositories.AccountRepository;
import fr.formation.spring.museum.security.IAccountDetailsProvider;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private IAccountDetailsProvider accountDetailsProvider;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	private  String dateFormat = "yyyy-MM-dd";
	
	@GetMapping(value = "/management")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView handleUserManagement(@RequestParam(value = "page", defaultValue = "0") int page) {
		
		final int RESULT_PER_PAGE = 1;
		
		// If using page=...&size=..., Spring automatically creates a Pageable for us. But we don't want that.
		PageRequest pageable = PageRequest.of(page, RESULT_PER_PAGE);

		ModelAndView mv = new ModelAndView("jsp/user/mgmt");
		mv.addObject("users", accountRepository.findAll(pageable).getContent());
		mv.addObject("dateFormat", dateFormat);
		mv.addObject("totalResults", accountRepository.count());
		mv.addObject("pageId", page);
		mv.addObject("resultPerPage", RESULT_PER_PAGE);

		// ...
		
		return mv;
	}
	
	@GetMapping(value = "/edit-profile")
	public ModelAndView handleUserEditProfile() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("jsp/user/profile_edit_form");
		mv.addObject("accountPasswordDTO", new AccountDTO());

		return mv;
	}
	
	@PostMapping(value = "/edit-profile")
	public String handleUserEditProfilePOST(Model model, RedirectAttributes ra, @ModelAttribute AccountDTO accountModel) {
		
		Account self = accountDetailsProvider.getAccount();
		
		// TODO: Skip checks if not editing self (implied admin access if thats the case)
		if (!accountModel.getNewPassword().equals(accountModel.getNewPasswordConfirmation())) {
			// TODO password mismatch, print error and allow edits
		}
		
		String oldHashedPassword = passwordEncoder.encode(accountModel.getOldPassword());
		if (!self.getPassword().equals(oldHashedPassword))
		{
			// TODO git gud and give me the actual password
		}

		String newHashedPassword = passwordEncoder.encode(accountModel.getNewPassword());
		self.setPassword(newHashedPassword);
		accountRepository.save(self);
		
		// TODO: Handle; flash attrs are weird
		// ra.addFlashAttribute("logout_reason", "label.info.loggedout.security");
		
		return "redirect:/logout";
	}
}
