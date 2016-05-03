package com.sample.app.model;

import org.springframework.data.annotation.Id;

public class Person {

	@Id 
	private String id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private String token;
	private boolean isTokenActive;
	
	
	
	public Person(String firstName,String lastName, String email,
			String password, String role, String token,boolean isTokenActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.token = token;
		this.isTokenActive = isTokenActive;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean getIsTokenActive() {
		return isTokenActive;
	}

	public void setIsTokenActive(boolean isTokenActive) {
		this.isTokenActive = isTokenActive;
	}
	
	
}
