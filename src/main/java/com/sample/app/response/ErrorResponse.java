package com.sample.app.response;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String errorCode;
	private String errorMessage;

}
