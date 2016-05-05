package com.sample.app.service;

import com.sample.app.request.CreateUserRequest;
import com.sample.app.response.CreateUserResponse;

public interface IUserService {
	public CreateUserResponse createUser(CreateUserRequest request);
}
