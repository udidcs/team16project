package com.example.team16project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class Team16projectApplication {
	public static void main(String[] args) {
		SpringApplication.run(Team16projectApplication.class, args);
	}

}
