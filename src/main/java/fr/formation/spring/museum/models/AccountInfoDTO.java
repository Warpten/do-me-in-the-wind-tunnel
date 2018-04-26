package fr.formation.spring.museum.models;

public class AccountInfoDTO {
	public int id;
	public String name;
	public String surname;
	
	public String oldPassword;
	public String newPassword;
	public String newPasswordConfirmation;
	
	public Locale preferredLocale;

	public Locale getPreferredLocale() {
		return preferredLocale;
	}
	public void setPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
