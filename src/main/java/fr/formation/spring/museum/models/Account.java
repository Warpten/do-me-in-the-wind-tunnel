package fr.formation.spring.museum.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.spring.museum.models.views.AccountModelView;
import fr.formation.spring.museum.models.views.RankModelView;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private int id;
	
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private String username;
	
	@Column(nullable = true)
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Internal.class })
	private String password;
	
	@Column(nullable = true, name="registration_date")
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Internal.class })
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;


	@ManyToMany()
	@JoinTable(
		name="account_ranks",
		joinColumns = @JoinColumn(name = "account_id"),
		inverseJoinColumns = @JoinColumn(name = "rank_id"))
	private Set<Rank> ranks;
	
	
	public int getId() { return this.id; }
	public String getUsername() { return this.username; }
	public String getPassword() { return this.password; }
	public Date getRegistrationDate() { return this.registrationDate; }

	public void setId(int id) { this.id = id; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
	
	public Set<Rank> getRanks() { return this.ranks; }
	public void setRanks(Set<Rank> ranks) { this.ranks = ranks; }
}
