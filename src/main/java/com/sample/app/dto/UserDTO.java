package com.sample.app.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.app.enums.Gender;
import com.sample.app.enums.Role;
import com.sample.app.errorcodes.UMSRequestExceptionConstants;
import com.sample.app.validation.annotation.DOB;
import com.sample.app.validation.annotation.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserDTO {
	
	@NotNull(message = UMSRequestExceptionConstants.INVALID_EMAIL)
	private String email;
	@NotNull(message = UMSRequestExceptionConstants.INVALID_NAME)
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String firstName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String middleName;
	@Name(message = UMSRequestExceptionConstants.INVALID_NAME)
	private String lastName;
	private String displayName;
	@NotNull(message = UMSRequestExceptionConstants.INVALID_GENDER)
	private Gender gender;
	@DOB(message = UMSRequestExceptionConstants.INVALID_DOB)
	private String dob;
	@NotNull
	private String mobileNumber;
	@NotNull(message = UMSRequestExceptionConstants.INVALID_ROLE)
	private Role role;
	@NotNull(message = UMSRequestExceptionConstants.BLANK_PASSWORD)
	private String password;
	private String isActive;
	
	private String userId;

}
