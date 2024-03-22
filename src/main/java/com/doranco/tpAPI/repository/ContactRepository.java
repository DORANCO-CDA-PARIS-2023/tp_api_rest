package com.doranco.tpAPI.repository;

import com.doranco.tpAPI.model.entity.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmailOrPhone(String email, String phone);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
