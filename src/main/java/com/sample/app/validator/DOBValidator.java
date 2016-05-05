package com.sample.app.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.sample.app.constant.CommonConstants;
import com.sample.app.errorcodes.UMSRequestExceptionCodes;
import com.sample.app.validation.annotation.DOB;

public class DOBValidator implements ConstraintValidator<DOB, String> {

	@Override
	public void initialize(DOB constraintAnnotation) {
	}

	@Override
	public boolean isValid(String dob, ConstraintValidatorContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		sdf.setLenient(false);
		Date dateOfBirth = null;
		String errorMsg = null;

		if (StringUtils.isBlank(dob)) // If dob is null then we should return
		{
			return true;
		}
		try {
			dateOfBirth = sdf.parse(dob);
		} catch (ParseException e) {
			errorMsg = UMSRequestExceptionCodes.INVALID_DOB.name();
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMsg)
					.addConstraintViolation();
			return false;
		}

		if (dateOfBirth == null) {
			setErrorMsgToContext(context,
					UMSRequestExceptionCodes.INVALID_DOB.name());
			return false;
		}

		if (dateOfBirth.getTime() > Calendar.getInstance().getTimeInMillis()) {
			setErrorMsgToContext(context,
					UMSRequestExceptionCodes.INVALID_DOB.name());
			return false;
		}

		return true;
	}

	private void setErrorMsgToContext(ConstraintValidatorContext context,
			String errorMsg) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMsg)
				.addConstraintViolation();
	}

}
