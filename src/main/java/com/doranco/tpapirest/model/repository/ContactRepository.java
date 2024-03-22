package com.doranco.tpapirest.model.repository;

import com.doranco.tpapirest.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByPhoneNumber(String phoneNumber);
    Optional<Contact> findByEmailContainingIgnoreCase(String email);
    List<Contact> findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(String firstname, String lastname);

}
