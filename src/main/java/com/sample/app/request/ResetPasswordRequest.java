package com.sample.app.request;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.sample.app.errorcodes.UMSRequestExceptionConstants;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	
	
	private String email;
	private String token;
	
	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
	@Nonnull
	private String confirmPassword;
}
