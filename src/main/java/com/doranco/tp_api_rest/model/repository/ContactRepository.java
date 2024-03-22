package com.doranco.tp_api_rest.model.repository;


import com.doranco.tp_api_rest.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> findByEmail(String email);
    List<Contact> findByPhoneNumber(String phoneNumber);
}