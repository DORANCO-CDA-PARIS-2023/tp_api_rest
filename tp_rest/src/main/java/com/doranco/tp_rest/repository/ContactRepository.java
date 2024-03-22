package com.doranco.tp_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doranco.tp_rest.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	public Contact findByNumberOrEmail(String numberOrEmail);
	
	@Query(value = "SELECT c FROM Contact c WHERE c.firstname LIKE :name OR c.lastname LIKE :name")
	public List<Contact> findByName(@Param("name") String name);
}
