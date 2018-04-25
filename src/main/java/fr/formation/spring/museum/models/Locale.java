package fr.formation.spring.museum.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locales")
public class Locale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String key;
	public int getId() { return id; }
	public String getKey() { return key; }
	public void setId(int id) { this.id = id; }
	public void setKey(String key) { this.key = key; }
}
