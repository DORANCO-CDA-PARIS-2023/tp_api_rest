package com.doranco.restapi.repository;


import com.doranco.restapi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> searchContacts(String firstName, String lastName);
}
