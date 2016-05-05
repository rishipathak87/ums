package com.sample.app.exception;

import lombok.Data;

@Data
public class UMSGenericException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public UMSGenericException(String errCode, String errMsg) {
		super(errMsg);
		this.errorCode = errCode;
		this.errorMessage = errMsg;
	}

	public UMSGenericException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errCode;
		this.errorMessage = message;
	}
}
