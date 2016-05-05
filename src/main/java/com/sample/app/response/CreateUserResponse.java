package com.sample.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CreateUserResponse {
	
	
	private String password;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String displayName;
	private String gender;
	private String dob;
	private String mobileNumber;
	private String role;
	private String token;
	private Date createdTime;
	private Date updatedTime;
	private String errorCode;
	private String errorMessage;
	private String isActive;
	

}
