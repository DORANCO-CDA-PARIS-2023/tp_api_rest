package com.doranco.correctiontpapi.controller;

import com.doranco.correctiontpapi.controller.payload.Payload;
import com.doranco.correctiontpapi.entity.Contact;
import com.doranco.correctiontpapi.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    private final ContactRepository contactRepository;
    
    public ApiController(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public ResponseEntity<Payload> addContact(@RequestBody Contact contact)
    {
        var data = this.contactRepository.save(contact);
        var payload = new Payload("Add new contact", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }


    @GetMapping("/contact")
    public ResponseEntity<Payload> getAllContact()
    {
        return new ResponseEntity<>(
                new Payload("Get All contact", contactRepository.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/contact/search")
    public ResponseEntity<Payload> searchContact(@RequestParam String email, @RequestParam String phone)
    {
        System.out.println("EMAIL :" + email);
        System.out.println("PHONE : " + phone);
        Payload payload = new Payload();
        var data = contactRepository.findByEmailContainingOrPhoneContaining(email, phone);
        System.out.println(data);
        if (data.isEmpty()) {
            payload.setMessage("Contact Not found ...");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        payload.setMessage("Get All contact by email or phone");
        payload.setData(data);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Payload> deleteContact(@PathVariable Long id)
    {
        contactRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Contact deleted"), HttpStatus.NO_CONTENT);
    }
}
