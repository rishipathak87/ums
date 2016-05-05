package com.sample.app.request;

import lombok.Data;

@Data
public class CreateUserRequest {

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

}
