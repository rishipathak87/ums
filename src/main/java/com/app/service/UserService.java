package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dao.UserDao;
import com.sample.app.model.User;
import com.sample.request.CreateUserRequest;
import com.sample.response.CreateUserResponse;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserDao userDao;

	public CreateUserResponse createUser(CreateUserRequest request) {

		log.info("calling create user api for request : " + request);

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		// List<User> list = getUserDetails(
		// / request.getEmailId());
		return null;

	}
}
