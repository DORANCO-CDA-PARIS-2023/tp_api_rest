package com.doranco.apirest.controller.api;

import com.doranco.apirest.payload.Payload;
import com.doranco.apirest.repository.IcontactRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ContactApiController {

    private final IcontactRepository contactRepository;

    public ContactApiController(IcontactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
 @GetMapping("/contact")
    public ResponseEntity<Payload> getAll(){
        var contacts = contactRepository.findAll();
       Payload payload = new Payload("Retrieving all contacts", contacts);
       if(contacts.isEmpty())
       {
           return new ResponseEntity<>(payload, HttpStatusCode.valueOf(204));
       }
       return new ResponseEntity<>(payload, HttpStatusCode.valueOf(200));
 }

 @GetMapping("/contact/{email}")
    public ResponseEntity<Payload> getBYEmail (@PathVariable String email) {
        Payload payload = new Payload();
        try {
            var contact = contactRepository.findByEmail(email);
            payload.setMessage("Get one contact");
            payload.setContent(contact);
            return new ResponseEntity<>(payload, HttpStatusCode.valueOf(302));
        } catch(NoSuchElementException e){
            payload.setMessage("No contact found");
            return new ResponseEntity<>(payload, HttpStatusCode.valueOf(204));
        }
 }
}
