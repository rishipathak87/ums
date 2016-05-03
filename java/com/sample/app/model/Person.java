package com.sample.app.model;

import org.springframework.data.annotation.Id;

public class Person {

	@Id 
	private String id;

	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String token;
	
	 public Person(String name, String email, String role, String token){
		 this.firstName = name;
		    this.email = email;
		    this.role = role;
		    this.token = token;
		  }
	
	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
