package com.doranco.tpapirest;

import com.doranco.tpapirest.model.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpApiRestApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ContactRepository contactRepository) {
		return args -> {

		};
	}

}
