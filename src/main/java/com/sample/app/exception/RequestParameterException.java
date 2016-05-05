package com.sample.app.exception;

import lombok.Data;

import com.sample.app.errorcodes.UMSRequestExceptionCodes;

@Data
public class RequestParameterException extends UMSGenericException {
	private static final long serialVersionUID = 1L;

	public RequestParameterException(String errCode, String errMsg) {
		super(errCode, errMsg);
	}

	public RequestParameterException() {
		super(UMSRequestExceptionCodes.INVALID_REQUEST.errCode(),
				UMSRequestExceptionCodes.INVALID_REQUEST.errMsg());
	}

}
