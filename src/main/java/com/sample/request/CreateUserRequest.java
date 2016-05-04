package com.sample.request;

import lombok.Data;

@Data
public class CreateUserRequest {

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

}
