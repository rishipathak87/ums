package com.sample.app.dto;

import com.sample.app.enums.Role;
import com.sample.app.errorcodes.UMSRequestExceptionConstants;
import com.sample.app.validation.annotation.DOB;
import com.sample.app.validation.annotation.Gender;
import com.sample.app.validation.annotation.Name;

import lombok.Data;

@Data
public class UserDTO {
	
	private String email;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String firstName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String middleName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String lastName;
	private String displayName;
	@Gender(message = UMSRequestExceptionConstants.INVALID_GENDER)
	private String gender;
	@DOB(message = UMSRequestExceptionConstants.INVALID_DOB)
	private String dob;
	private String mobileNumber;
	private Role role;
	private String password;
	private String isActive;

}
