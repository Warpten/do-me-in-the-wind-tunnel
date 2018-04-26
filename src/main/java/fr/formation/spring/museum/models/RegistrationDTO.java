package fr.formation.spring.museum.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegistrationDTO {

	@NotBlank(message = "error.form.register.blank_name")
	private String name;
	
	@NotBlank(message = "error.form.register.blank_surname")
	private String surname;
	
	@NotBlank(message = "error.form.register.blank_username")
	private String username;

	@NotBlank(message = "error.form.register.blank_password")
	private String password;
	
	@Email(regexp = "[a-z0-9._-]+(?:\\+[a-z0-9_.-]+)?@[a-z0-9._-]+(\\.[a-z]+)+",
			message = "error.form.register.invalid_email_format")
	private String email;

	private Locale preferredLocale;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Locale getPreferredLocale() {
		return preferredLocale;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}
}
