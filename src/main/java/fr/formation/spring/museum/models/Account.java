package fr.formation.spring.museum.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.spring.museum.models.views.AccountModelView;
import fr.formation.spring.museum.models.views.RankModelView;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = -3276168618619336563L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private int id;

	private String email;
	
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private String username;
	
	@Column(nullable = true)
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Internal.class })
	private String password;
	
	@Column(nullable = true, name = "registration_date")
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Internal.class })
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "pref_locale")
	private Locale preferredLocale;
	
	private String name;
	private String surname;

	@ManyToMany()
	@JoinTable(
		name="account_ranks",
		joinColumns = @JoinColumn(name = "account_id"),
		inverseJoinColumns = @JoinColumn(name = "rank_id"))
	private Set<Rank> ranks = new HashSet<>();
	
	private boolean enabled;
	
	
	public int getId() { return this.id; }
	public String getUsername() { return this.username; }
	public String getPassword() { return this.password; }
	public Date getRegistrationDate() { return this.registrationDate; }
	public String getName() { return this.name; }
	public String getSurname() { return this.surname; }
	public Locale getPreferredLocale() { return this.preferredLocale; }
	public boolean isEnabled() { return this.enabled; }
	public String getEmail() { return this.email; }

	public void setId(int id) { this.id = id; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
	public void setName(String name) { this.name = name; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setPreferredLocale(Locale locale) { this.preferredLocale = locale; }
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	public void setEmail(String email) { this.email = email; }
	
	public Set<Rank> getRanks() { return this.ranks; }
	public void setRanks(Set<Rank> ranks) { this.ranks = ranks; }
	public void setRank(Rank rank) { this.ranks.add(rank); }
}
