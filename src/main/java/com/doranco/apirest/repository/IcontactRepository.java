package com.doranco.apirest.repository;

import com.doranco.apirest.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcontactRepository extends JpaRepository<Contact, Integer>{
   public Contact findByEmail(String email);
    //Contact findByFirstName(String firstName);
}
