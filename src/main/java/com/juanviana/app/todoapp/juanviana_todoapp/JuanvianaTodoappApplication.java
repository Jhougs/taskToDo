package com.juanviana.app.todoapp.juanviana_todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class JuanvianaTodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuanvianaTodoappApplication.class, args);
	}

}
