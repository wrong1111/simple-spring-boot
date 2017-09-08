package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.dao")
@EntityScan(basePackages = "com.example.model")
public class SimpleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootApplication.class, args);
	}
}
