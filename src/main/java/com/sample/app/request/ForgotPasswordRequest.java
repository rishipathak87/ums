package com.sample.app.request;

import org.hibernate.validator.constraints.NotBlank;

import com.sample.app.errorcodes.UMSRequestExceptionConstants;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
	
	@NotBlank(message = UMSRequestExceptionConstants.INVALID_EMAIL)
	private String email;
}
