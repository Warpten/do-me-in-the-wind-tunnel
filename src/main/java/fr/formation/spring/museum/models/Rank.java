package fr.formation.spring.museum.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.spring.museum.models.views.AccountModelView;
import fr.formation.spring.museum.models.views.RankModelView;

@Entity
public class Rank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private int id;
	
	@JsonView(value = { AccountModelView.Public.class, RankModelView.Public.class })
	private String name;
	
	private String authority;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ranks")
	@JsonView(value = { AccountModelView.Internal.class, RankModelView.Public.class })
	private Set<Account> users;
	
	// Getters and setters
	
	public int getId() { return this.id; }
	public String getName() { return this.name; }
	
	public String getAuthority() { return this.authority; }
	public void setAuthority(String authority) { this.authority = authority; }
	
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	
	@Transactional(readOnly = false)
	public Set<Account> getUsers() { return this.users; }
	public void setUsers(Set<Account> users) { this.users = users; }
}
