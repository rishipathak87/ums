package com.sample.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.sample.app.errorcodes.UMSRequestExceptionCodes;
import com.sample.app.validation.annotation.Name;

public class NameValidator implements ConstraintValidator<Name, String> {

	private static final String NAME_PATTERN = "^(([a-zA-Z0-9_-][a-zA-Z0-9 _-]*[a-zA-Z0-9_-])|([a-zA-Z0-9_-]))$";

	@Override
	public void initialize(Name constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(name)) {
			return true;
		} else {
			if (name.length() > 50) {
				String errorMsg = UMSRequestExceptionCodes.INVALID_NAME
						.errMsg();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(errorMsg)
						.addConstraintViolation();
				return false;
			}
			if (!name.matches(NAME_PATTERN)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						UMSRequestExceptionCodes.INVALID_NAME.errMsg())
						.addConstraintViolation();
				return false;
			}
		}
		return true;
	}
}
