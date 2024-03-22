package com.doranco.restapi.service;



import com.doranco.restapi.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    Contact addContact();
    void deleteContact(Long id);
    List<Contact> searchContacts(String firstName, String lastName);
}
