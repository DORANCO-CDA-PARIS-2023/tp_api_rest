package com.doranco.restapi.service;



import com.doranco.restapi.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    Contact addContact(Contact contact);
    void deleteContact(Long id);
    List<Contact> searchContacts(String firstName, String lastName);
}
