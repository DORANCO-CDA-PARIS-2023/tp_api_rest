package com.doranco.tpapirest.controller.api;

import com.doranco.tpapirest.model.entity.Contact;
import com.doranco.tpapirest.model.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactApiController {

    private final ContactRepository contactRepository;

    public ContactApiController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts")
    public ResponseEntity<Payload> getAllContact() {
        Payload payload = new Payload();
        List<Contact> contacts = contactRepository.findAll();

        if (contacts.isEmpty()) {
            payload.setMessage("There are no contacts to retrieve.");
            payload.setContent(contacts);
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "Successfully retrieve %d %s",
                contacts.size(),
                contacts.size() > 1 ? "contacts" : "contact"
        ));
        payload.setContent(contacts);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<Payload> addContact(@RequestBody Contact bodyContact) {
        Payload payload = new Payload();

        if (bodyContact.getFirstname() == null) {
            payload.setMessage("Invalid contact.");
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }

        if (bodyContact.getPhoneNumber() != null) {
            Optional<Contact> contactByPhoneNumber = contactRepository.findByPhoneNumber(bodyContact.getPhoneNumber());
            if (contactByPhoneNumber.isPresent()) {
                payload.setMessage(String.format(
                        "Phone number %s is already registered.",
                        bodyContact.getPhoneNumber()
                ));
                return new ResponseEntity<>(payload, HttpStatus.CONFLICT);
            }
        }

        Contact savedContact = contactRepository.save(bodyContact);
        payload.setMessage("Contact added.");
        payload.setContent(savedContact);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Payload> deleteById(@PathVariable(name = "id") Long id) {
        Payload payload = new Payload();

        Optional<Contact> contactById = contactRepository.findById(id);
        if (contactById.isEmpty()) {
            payload.setMessage(String.format(
                    "There is no contact with id %s.",
                    id
            ));
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        payload.setMessage(String.format(
                "Contact id %s deleted.",
                id
        ));
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("/contacts/search")
    public ResponseEntity<Payload> getByFullname(@RequestParam(name = "firstname") String firstname,
                                                 @RequestParam(name = "lastname") String lastname) {
        Payload payload = new Payload();

        List<Contact> contacts = contactRepository.findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(firstname, lastname);
        if (contacts.isEmpty()) {
            payload.setMessage(String.format(
                    "There are no contact with fullname %s %s.",
                    lastname,
                    firstname
            ));
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "%d %s found with fullname %s %s.",
                contacts.size(),
                contacts.size() > 1 ? "contacts" : "contact",
                lastname,
                firstname
        ));
        payload.setContent(contacts);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
