package com.doranco.restapi.service;


import com.doranco.restapi.model.Contact;
import com.doranco.restapi.repository.ContactRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Resource
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }




    @Override
    public Contact addContact() {
        contactRepository.save();
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> searchContacts(String firstName, String lastName) {
        return contactRepository.searchContacts(firstName, lastName);
    }
}
