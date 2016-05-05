package com.sample.app.model;

import java.util.Date;

import lombok.Data;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class User {

	@Id 
	private String userId;

	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String password;
	private String displayName;
	private String role;
	private Date createdTime;
	private Date updatedTime;
	private boolean isActive;
	private String gender;
	private String mobileNumber;
	
	//yyyy-mm-dd
	private String dob;
	
	
	
}
