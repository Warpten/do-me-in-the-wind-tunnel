package fr.formation.spring.museum.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect @Configuration
public class SecurityAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
