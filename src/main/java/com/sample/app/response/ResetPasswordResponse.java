package com.sample.app.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResetPasswordResponse {
	private String errorCode;
	private String errorMessage;
	private String status;
}
