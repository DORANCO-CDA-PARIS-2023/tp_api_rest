package com.doranco.tp_api_rest.controller.api;

import com.doranco.tp_api_rest.model.entity.Contact;
import com.doranco.tp_api_rest.model.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactApiController {

    private final ContactRepository contactRepository;

    public ContactApiController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/api/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    
    @GetMapping("/api/contacts/info")
    public ResponseEntity<List<Contact>> getContactsByEmailOrPhoneNumber(@RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber) {
        List<Contact> contactsByEmail = contactRepository.findByEmail(email);
        List<Contact> contactsByPhoneNumber = contactRepository.findByPhoneNumber(phoneNumber);
        List<Contact> combinedResults = new ArrayList<>();
        combinedResults.addAll(contactsByEmail);
        combinedResults.addAll(contactsByPhoneNumber);

        if (!combinedResults.isEmpty()) {
            return new ResponseEntity<>(combinedResults, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/contacts/searchByName")
    public ResponseEntity<List<Contact>> searchContactsByName(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        List<Contact> contacts = contactRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/contacts")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/contacts/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable("id") long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}