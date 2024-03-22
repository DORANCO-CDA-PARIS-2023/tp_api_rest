package com.doranco.tp_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table
public class Contact {
	
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String number;
	private String adress;
	private String company;
	
	public Contact() {
		
	}

	public Contact(String firstname, String lastname, String email, String number, String adress, String company) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.number = number;
		this.adress = adress;
		this.company = company;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	

}
