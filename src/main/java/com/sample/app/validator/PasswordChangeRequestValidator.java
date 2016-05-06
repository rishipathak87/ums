package com.sample.app.validator;

import org.apache.commons.lang.StringUtils;

import com.sample.app.request.ResetPasswordRequest;

public class PasswordChangeRequestValidator {
	
	static public String validatePasswordChangeRequest(ResetPasswordRequest request){
		String result="";
		if( (StringUtils.isEmpty(request.getOldPassword())) || (StringUtils.isEmpty(request.getEmail()))
			||	(StringUtils.isEmpty(request.getNewPassword())) || (StringUtils.isEmpty(request.getConfirmPassword()))	){
			result = "Invalid Request. Email ,Old Password, New Password & Confirm Password are mandatory";
			return result;
		} else if(request.getNewPassword() != request.getConfirmPassword()){
			result = "New Password and Confirm Password should be same";
			return result;
		}else if (request.getNewPassword().length() < 6 ){
			result = "New Password should be atleast 6 characters";
			return result;
		}else
		result = "Valid Request";
		return result;
	}

}
