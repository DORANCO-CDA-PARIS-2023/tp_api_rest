package com.doranco.restapi.repository;


import com.doranco.restapi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> searchContacts(String firstName, String lastName);

    void save();
}
