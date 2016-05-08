package com.sample.app.service;

import com.sample.app.request.CreateUserRequest;
import com.sample.app.request.GetUserRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.response.ForgotPasswordResponse;
import com.sample.app.response.GetUserResponse;

public interface IUserService {
	public CreateUserResponse createUser(CreateUserRequest request) throws Exception;

	public GetUserResponse getUserByEmail(GetUserRequest request) throws Exception;

	ForgotPasswordResponse forgotPassword(String email);

}
