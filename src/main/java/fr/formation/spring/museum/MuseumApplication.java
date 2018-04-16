package fr.formation.spring.museum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The @SpringBootApplication annotation adds the following annotations:
 *
 * @Configuration – which marks the class as a source of bean definitions
 * @EnableAutoConfiguration – which tells the framework to add beans based on
 *                          the dependencies on the classpath automatically
 * @ComponentScan – which scans for other configurations and beans in the same
 *                package as the Application class or below
 *
 */
@SpringBootApplication
public class MuseumApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MuseumApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(MuseumApplication.class, args);
	}

}
