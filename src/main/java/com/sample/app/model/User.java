package com.sample.app.model;

import java.sql.Timestamp;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class User {

	@Id 
	private String userId;

	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private boolean isActive;
	private String Gender;
	//yyyy-mm-dd
	private String dob;
	
}
