package com.demo.project;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.project.Repository.UserRepository;
import com.demo.project.entity.User;

@SpringBootApplication
public class SpringBootAngularCrudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularCrudUserApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			Stream.of("seeta", "geeta", "neeta", "riya", "siya").forEach(name -> {
				User user = new User(name, name.toLowerCase() + "@gmail.com");
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
