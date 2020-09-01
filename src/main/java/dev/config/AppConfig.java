package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("dev")
@PropertySource("app.properties")
public class AppConfig {
	
	//constructeur de bean
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

}
