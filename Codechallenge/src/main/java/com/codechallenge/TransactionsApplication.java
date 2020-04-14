package com.codechallenge;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionsApplication {
	public static void main(final String[] args) {
		SpringApplication app = new SpringApplication(TransactionsApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
		app.run(args);
	}
}
