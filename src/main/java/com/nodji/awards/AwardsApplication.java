package com.nodji.awards;

import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.nodji.awards.service.MovieService;

@SpringBootApplication()
public class AwardsApplication {

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
		SpringApplication.run(AwardsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext appContext) {
		return args -> movieService.chargeDate(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("movielist.csv")));
	}

}
