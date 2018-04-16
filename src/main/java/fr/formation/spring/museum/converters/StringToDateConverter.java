package fr.formation.spring.museum.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			return df.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

}
