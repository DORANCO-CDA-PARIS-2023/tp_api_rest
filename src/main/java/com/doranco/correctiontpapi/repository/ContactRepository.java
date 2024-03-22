package com.doranco.correctiontpapi.repository;

import com.doranco.correctiontpapi.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public List<Contact> findAllByEmailOrPhone(String email, String phone);

    @Query("SELECT c FROM Contact c WHERE c.email LIKE %:email%")
    public List<Contact> searchByEmail(@Param("email") String email);

    public List<Contact> findByEmailContainingOrPhoneContaining(String email, String phone);
}
