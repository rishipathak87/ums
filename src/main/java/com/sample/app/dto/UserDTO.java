package com.sample.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.sample.app.enums.Gender;
import com.sample.app.enums.Role;
import com.sample.app.errorcodes.UMSRequestExceptionConstants;
import com.sample.app.validation.annotation.DOB;
import com.sample.app.validation.annotation.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String email;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String firstName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String middleName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String lastName;
	private String displayName;
	private Gender gender;
	@DOB(message = UMSRequestExceptionConstants.INVALID_DOB)
	private String dob;
	private String mobileNumber;
	private Role role;
	private String password;
	private String isActive;

}
