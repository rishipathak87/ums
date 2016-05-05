package com.sample.app.request;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import com.sample.app.dto.UserDTO;
import com.sample.app.errorcodes.UMSRequestExceptionConstants;

@Data
public class CreateUserRequest {

	@NotBlank(message = UMSRequestExceptionConstants.INVALID_PASSWORD)
	private UserDTO userDto;

}
