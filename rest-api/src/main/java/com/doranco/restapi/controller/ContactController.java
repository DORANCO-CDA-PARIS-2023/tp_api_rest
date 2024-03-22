package com.doranco.restapi.controller;


import com.doranco.restapi.model.Contact;
import com.doranco.restapi.service.ContactService;
import com.doranco.restapi.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {


     ContactServiceImpl contactService=new ContactServiceImpl();

    @Autowired
    public void setContactService(ContactServiceImpl contactService){
        this.contactService= contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.addContact();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchContacts(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        List<Contact> contacts = contactService.searchContacts(firstName, lastName);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
