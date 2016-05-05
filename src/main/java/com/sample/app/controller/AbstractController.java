package com.sample.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.errorcodes.UMSGenericExceptionCodes;
import com.sample.app.exception.RequestParameterException;
import com.sample.app.exception.UMSGenericException;
import com.sample.app.response.ExceptionResponse;

@RestController
@Slf4j
public class AbstractController {

	@ExceptionHandler({ IOException.class })
	@ResponseBody
	public ExceptionResponse handleIOException(IOException ex,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		log.error("IO Exception occoured :", ex);
		ExceptionResponse exception = new ExceptionResponse();
		exception
				.setErrorMessage(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errMsg());
		exception
				.setErrorCode(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errCode());
		httpResponse.setStatus(500);
		return exception;
	}

	@ExceptionHandler({ RequestParameterException.class })
	@ResponseBody
	public ExceptionResponse handleRequestParameterException(
			RequestParameterException ex, HttpServletRequest request,
			HttpServletResponse httpResponse) {
		log.error("IO Exception occoured :", ex);
		ExceptionResponse exception = new ExceptionResponse();
		exception.setErrorMessage(ex.getErrorCode());
		exception.setErrorCode(ex.getErrorMessage());
		httpResponse.setStatus(500);
		return exception;
	}

	@ExceptionHandler({ UMSGenericException.class })
	@ResponseBody
	public ExceptionResponse handleGenericParameterException(
			UMSGenericException ex, HttpServletRequest request,
			HttpServletResponse httpResponse) {
		log.error("IO Exception occoured :", ex);
		ExceptionResponse exception = new ExceptionResponse();
		exception.setErrorMessage(ex.getErrorCode());
		exception.setErrorCode(ex.getErrorMessage());
		httpResponse.setStatus(500);
		return exception;
	}

	@ExceptionHandler({ RuntimeException.class })
	@ResponseBody
	public ExceptionResponse handleRuntimeException(RuntimeException ex,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		ExceptionResponse exception = new ExceptionResponse();
		exception
				.setErrorMessage(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errMsg());
		exception
				.setErrorCode(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errCode());
		httpResponse.setStatus(500);
		return exception;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ExceptionResponse handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		log.error("Exception occoured :", ex);
		ExceptionResponse exception = new ExceptionResponse();
		exception
				.setErrorMessage(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errMsg());
		exception
				.setErrorCode(UMSGenericExceptionCodes.APPLICATION_ERROR_OCCURED
						.errCode());
		httpResponse.setStatus(500);
		return exception;
	}

}
