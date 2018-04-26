package fr.formation.spring.museum;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.formation.spring.museum.converters.IntegerToLocaleConverter;
import fr.formation.spring.museum.converters.StringToDateConverter;
import fr.formation.spring.museum.interceptors.PerformanceInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("fr.formation.spring.museum")
public class WebConfiguration implements WebMvcConfigurer {
	
	// Equivalent to mvc:interceptors.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(performanceInterceptor()); // too spammy
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	// Adds handlers for static resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
	}
	
	/**
	 * Why is this needed?
	 * 
	 * By simply doing <pre>registry.addInterceptor(new PerformanceInterceptor())</pre>,
	 * we are not giving lifecycle control of the interceptor to Spring.
	 * 
	 * If we however declare our interceptor as a bean, Spring can then manage it,
	 * and autowire anything within it for us.
	 */
	public @Bean PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}
	

	// Add converters and formatters to be leveraged by Spring MVC when dealing with forms (and other things?)
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(integerToLocaleConverter());
        // registry.addConverter(new IntegerToRankConverter());
    }
	
	public @Bean IntegerToLocaleConverter integerToLocaleConverter() {
		return new IntegerToLocaleConverter();
	}
	
	// Retrieved from application.properties
   
    private @Value("${spring.view.prefix}")     String prefix = "/WEB-INF/jsp/";
    private @Value("${spring.view.suffix}")     String suffix = ".jsp";
    private @Value("${spring.view.view-names}") String viewNames = "jsp/*";

	// Configure a view resolver for JSPs
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setViewNames(viewNames);
        registry.viewResolver(resolver);
	}
	
	// Configure a locale resolver for messages_XX.properties
	public @Bean LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("my-locale-cookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
	}

	// I forget what this is
	public @Bean LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("locale");
		return lci;
	}
	
	public @Bean ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		bundle.setBasename("messages");
		bundle.setDefaultEncoding("UTF-8");
		
		bundle.setFallbackToSystemLocale(false);
		bundle.setCacheMillis(0);
		return bundle;
	}
}
