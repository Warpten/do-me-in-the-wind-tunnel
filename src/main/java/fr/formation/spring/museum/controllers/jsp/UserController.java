package fr.formation.spring.museum.controllers.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.spring.museum.models.Account;
import fr.formation.spring.museum.models.AccountInfoDTO;
import fr.formation.spring.museum.repositories.AccountRepository;
import fr.formation.spring.museum.repositories.LocaleRepository;
import fr.formation.spring.museum.security.IAccountDetailsProvider;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private IAccountDetailsProvider accountDetailsProvider;
	
	@Autowired
	private LocaleRepository localeRepository;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/edit-profile")
	public ModelAndView handleUserEditProfile() {
		ModelAndView mv = new ModelAndView();
		
		Account self = accountDetailsProvider.getAccount();
		AccountInfoDTO dto = new AccountInfoDTO();
		dto.name = self.getName();
		dto.surname = self.getSurname();
		dto.preferredLocale = self.getPreferredLocale();
		
		mv.setViewName("jsp/user/profile_edit_form");
		mv.addObject("accountInfo", dto);
		mv.addObject("locales", localeRepository.findAll());

		return mv;
	}
	
	@PostMapping(value = "/edit-profile")
	public String handleUserEditProfilePOST(Model model, RedirectAttributes ra, @ModelAttribute AccountInfoDTO accountModel) {
		
		boolean passwordSet = false;
		model.addAttribute("accountInfo", accountModel);
		model.addAttribute("locales", localeRepository.findAll());
		
		Account self = accountDetailsProvider.getAccount();

		if (!accountModel.getNewPassword().equals(accountModel.getNewPasswordConfirmation())) {
			model.addAttribute("global_error_mismatch", "label.form.user.settings.error.passwordMismatch");
			return "jsp/user/profile_edit_form";
		}
		 
		if (!passwordEncoder.matches(accountModel.getOldPassword(), self.getPassword())) {
			model.addAttribute("global_error", "label.form.user.settings.error.invalidPassword");
			return "jsp/user/profile_edit_form";
		}

		if (accountModel.getNewPassword().trim().length() != 0) {
			passwordSet = true;
			String newHashedPassword = passwordEncoder.encode(accountModel.getNewPassword());
			self.setPassword(newHashedPassword);
		}
		
		if (accountModel.getName() != null && !StringUtils.isEmpty(accountModel.getName().trim())) {
			 self.setName(accountModel.getName());
		}
		
		if (accountModel.getSurname() != null && !StringUtils.isEmpty(accountModel.getSurname().trim())) {
			 self.setSurname(accountModel.getSurname());
		}
		
		self.setPreferredLocale(accountModel.getPreferredLocale());
		
		accountRepository.save(self);
		
		// TODO: Handle; flash attrs are weird
		// ra.addFlashAttribute("logout_reason", "label.info.loggedout.security");
		
		return passwordSet ? "redirect:/logout" : "jsp/user/profile_edit_form";
	}
}
