package com.sample.app.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

import com.sample.app.errorcodes.UMSRequestExceptionConstants;

@Data
public class GetUserRequest {
	private static final long serialVersionUID = 1L;
	private String token;
	@NotNull(message=UMSRequestExceptionConstants.INVALID_EMAIL)
	private String email;
}
