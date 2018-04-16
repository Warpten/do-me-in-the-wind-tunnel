package fr.formation.spring.museum.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.formation.spring.museum.models.Account;

@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty");
	}
}
