package fr.formation.spring.museum.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import fr.formation.spring.museum.models.Rank;
import fr.formation.spring.museum.repositories.RankRepository;
import net.minidev.asm.ex.ConvertException;


public class IntegerToRankConverter implements Converter<String, Rank>
{
	@Autowired
	public RankRepository rankRepository;
	
	@Override
	public Rank convert(String sourceStr) {
		int source = Integer.parseInt(sourceStr);
		Optional<Rank> rank = rankRepository.findById(source);
		if (rank.isPresent())
			return rank.get();

		throw new ConvertException("Unknown rank id " + source);
	}
}