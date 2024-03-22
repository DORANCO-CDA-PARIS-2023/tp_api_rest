package com.doranco.tp_rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.doranco.tp_rest.entity.Contact;
import com.doranco.tp_rest.repository.ContactRepository;


@SpringBootApplication
public class TpRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpRestApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ContactRepository contactRepository) {
		return args -> {
			Contact contact1 = new Contact("Didier", "Contact", "contact@fake.com", "0615", "2, rue Random", "Doranco");
			Contact contact2 = new Contact("Didier", "Test", "test@fake.com", "0712", "9, avenue Bullshit", "Total");
			Contact contact3 = new Contact("Albert", "Contact", "ac@fake.com", "0728", "17, boulevard Padidée", "Crédit Agricole");
			Contact contact4 = new Contact("Jean-Michel", "Machin", "jmm@fake.com", "0609420", "31, impasse de l'Imagination", "Renault");
			contactRepository.save(contact1);
			contactRepository.save(contact2);
			contactRepository.save(contact3);
			contactRepository.save(contact4);

		};
	}
}
