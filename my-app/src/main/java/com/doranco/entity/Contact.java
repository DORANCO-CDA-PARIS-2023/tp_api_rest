package com.doranco.entity;

public class Contact {
	
	private String firstname;
	private String lastname;
	private String number;
	private String email;
	private String adresse;
	private String company;
	
	public Contact() {
		
	}
	
	public Contact(String firstname, String lastname, String number, String email, String adresse, String company) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.number = number;
		this.email = email;
		this.adresse = adresse;
		this.company = company;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	
	

}
