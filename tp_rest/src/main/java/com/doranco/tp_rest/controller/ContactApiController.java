package com.doranco.tp_rest.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.tp_rest.entity.Contact;
import com.doranco.tp_rest.payload.Payload;
import com.doranco.tp_rest.repository.ContactRepository;

@RestController
@RequestMapping("/tprestapi")
public class ContactApiController {
	
	private final ContactRepository contactRepository;

	public ContactApiController(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}
	
    @GetMapping("/contact")
    public ResponseEntity<Payload> getAll()
    {
        var contacts = contactRepository.findAll();
        Payload payload = new Payload("Get all contacts", contacts);
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("/contact/number/{numberOrEmail}")
    public ResponseEntity<Payload> getByNumberOrEmail(@PathVariable String numberOrEmail)
    {
        Payload payload = new Payload();
        try {
            var contact = contactRepository.findByNumberOrEmail(numberOrEmail);
            payload.setMessage("Get one Contact");
            payload.setContent(contact);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Contact not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<Payload> add(@RequestBody Contact contact)
    {
        Payload payload = new Payload();
        contact = contactRepository.save(contact);
        payload.setContent(contact);
        payload.setMessage("Article created");
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }


    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Payload> deleteById(@PathVariable int id) {
    	contactRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Contact with ID : " + id + " deleted"), HttpStatus.OK);
    }
    
    @GetMapping("/contact/name/{name}")
    public ResponseEntity<Payload> getByName(@PathVariable String name)
    {
        var contacts = contactRepository.findAll();
        Payload payload = new Payload("Get all contacts", contacts);
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
