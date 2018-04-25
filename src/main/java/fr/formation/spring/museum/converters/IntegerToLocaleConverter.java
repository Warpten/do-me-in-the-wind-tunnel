package fr.formation.spring.museum.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.formation.spring.museum.models.Locale;
import fr.formation.spring.museum.repositories.LocaleRepository;

@Component
public class IntegerToLocaleConverter implements Converter<String, Locale>
{
	@Autowired
	@Lazy(true)
	public LocaleRepository localeRepository;
	
	@Override
	public Locale convert(String sourceStr) {
		int source = Integer.parseInt(sourceStr);
		Optional<Locale> rank = localeRepository.findById(source);
		if (rank.isPresent())
			return rank.get();

		throw new IllegalArgumentException("Unknown locale id " + source);
	}
}