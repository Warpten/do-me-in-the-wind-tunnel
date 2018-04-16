package fr.formation.spring.museum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.spring.museum.models.Rank;
import fr.formation.spring.museum.models.views.RankModelView;
import fr.formation.spring.museum.repositories.RankRepository;

@Controller
@RequestMapping(path = "/ranks")
public class JsonRankController {
	@Autowired
	private RankRepository rankRepository;

	@GetMapping(path = "/enumerate/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(RankModelView.Public.class)
	public @ResponseBody Iterable<Rank> getAllAccountsJSON() {
		return rankRepository.findAll();
	}
	
	@GetMapping(path = "/details/{rankName}/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(RankModelView.Public.class)
	public @ResponseBody Rank getAccountDetailsJSON(
			@PathVariable(name = "name") String userName) {
		return rankRepository.findByName(userName);
	}
	
	@GetMapping(path = "/enumerate/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Iterable<Rank> getAllAccountsXML() {
		return rankRepository.findAll();
	}
	
	@GetMapping(path = "/details/{rankName}/xml", produces = MediaType.APPLICATION_XML_VALUE)
	@JsonView(RankModelView.Public.class)
	public @ResponseBody Rank getAccountDetailsXML(
			@PathVariable(name = "name") String userName) {
		return rankRepository.findByName(userName);
	}
}
