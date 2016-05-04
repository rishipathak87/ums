package com.sample.response;

import lombok.Data;

@Data
public class CreateUserResponse {

	private String password;
	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String displayName;
	private String gender;
	private String dob;
	private String mobileNumber;
	private String userRole;
	private String token;

}
