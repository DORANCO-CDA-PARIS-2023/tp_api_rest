package com.doranco.tp_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doranco.tp_rest.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	@Query(value = "SELECT c FROM Contact c WHERE c.email LIKE :numberOrEmail OR c.number LIKE :numberOrEmail")
	public Contact findByNumberOrEmail(String numberOrEmail);
	
	@Query(value = "SELECT * FROM contact c WHERE c.firstname LIKE %:name% OR c.lastname LIKE %:name%", nativeQuery=true)
	public List<Contact> findByName(@Param("name") String name);
	
//	public List<Contact> findByFirstnameContainingOrLastnameContaining(String name);
}
