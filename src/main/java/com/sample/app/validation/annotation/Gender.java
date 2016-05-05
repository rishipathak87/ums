package com.sample.app.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sample.app.validator.DOBValidator;


@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DOBValidator.class })
@Documented
public @interface Gender {
	String message() default "";
	
	boolean checkUnderAge() default false;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
