package fr.formation.spring.museum;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.formation.spring.museum.converters.IntegerToRankConverter;
import fr.formation.spring.museum.converters.StringToDateConverter;
import fr.formation.spring.museum.interceptors.PerformanceInterceptor;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	
	/**
	 * Equivalent to mvc:interceptors.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(performanceInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/user/login.html");
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
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new IntegerToRankConverter());
    }
	
    @Value("${spring.view.prefix}")
    private String prefix = "/WEB-INF/jsp/";

    @Value("${spring.view.suffix}")
    private String suffix = ".jsp";

    @Value("${spring.view.view-names}")
    private String viewNames = "jsp/*";

	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setViewNames(viewNames);
        registry.viewResolver(resolver);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("locale");
		return lci;
	}

}
