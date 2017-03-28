package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mmaheo on 09/03/2017.
 */
@SpringBootApplication
@ComponentScan({"controller"})
@EnableWebSecurity
public class Application extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("louis").password("louis").roles("USER").and()
            .withUser("bastien").password("bastien").roles("USER").and()
            .withUser("selim").password("selim").roles("USER").and()
            .withUser("leo").password("leo").roles("USER").and()
            .withUser("maxime").password("maxime").roles("USER");
    }
}
