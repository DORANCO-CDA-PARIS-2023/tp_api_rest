package com.doranco.coursSpring.repository;

import com.doranco.coursSpring.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmailOrPhoneNumber(String email, String phoneNumber);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
