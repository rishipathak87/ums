package com.sample.app.service;

import com.sample.app.request.CreateUserRequest;
import com.sample.app.request.GetUserRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.response.ForgotPasswordResponse;
import com.sample.app.response.GetUserResponse;

public interface IUserService {
	public CreateUserResponse createUser(CreateUserRequest request);
	
//	public GetUserResponse getUserByEmail(GetUserRequest request);

	public GetUserResponse getUserByEmail(String email);

	ForgotPasswordResponse getPassByEmail(String email);
}
