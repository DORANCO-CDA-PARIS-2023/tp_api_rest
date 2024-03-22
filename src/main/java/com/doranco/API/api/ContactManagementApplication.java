package com.doranco.API.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.API.api.payload.Payload;
import com.doranco.API.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/api/contacts")
public class ContactManagementApplication {

    private List<Contact> contacts = new ArrayList<>();
    private long nextId = 1;

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApplication.class, args);
    }

    @GetMapping
    public ResponseEntity<Payload> getAllContacts() {
        Payload payload = new Payload();
        if (contacts.isEmpty()) {
            payload.setMessage("No contacts found");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        } else {
            payload.setMessage("Get all contacts");
            payload.setContent(contacts);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        }
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Payload> getContactByIdentifier(@PathVariable String identifier) {
        Payload payload = new Payload();
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(identifier) || contact.getPhoneNumber().equals(identifier)) {
                payload.setMessage("Get contact by identifier");
                payload.setContent(contact);
                return new ResponseEntity<>(payload, HttpStatus.OK);
            }
        }
        payload.setMessage("Contact not found");
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Payload> addContact(@RequestBody Contact contact) {
        contact.setId(nextId++);
        contacts.add(contact);
        Payload payload = new Payload("Contact created", contact);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payload> deleteContactById(@PathVariable long id) {
        Contact deletedContact = null;
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                deletedContact = contact;
                contacts.remove(contact);
                break;
            }
        }
        if (deletedContact != null) {
            Payload payload = new Payload("Contact deleted", deletedContact);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            Payload payload = new Payload("Contact not found", null);
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Payload> searchContactsByName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        List<Contact> foundContacts = contacts.stream()
                .filter(contact ->
                        (firstName == null || contact.getFirstName().equals(firstName)) &&
                        (lastName == null || contact.getLastName().equals(lastName)))
                .collect(Collectors.toList());

        if (!foundContacts.isEmpty()) {
            Payload payload = new Payload("Search contacts", foundContacts);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            Payload payload = new Payload("Contacts not found", null);
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
}