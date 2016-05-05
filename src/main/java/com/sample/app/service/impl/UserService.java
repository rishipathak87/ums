package com.sample.app.service.impl;

import java.sql.Timestamp;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dao.UserDao;
import com.sample.app.dto.UserDTO;
import com.sample.app.errorcodes.UMSGenericExceptionCodes;
import com.sample.app.exception.UMSGenericException;
import com.sample.app.mapper.UMSServiceObjectMapper;
import com.sample.app.model.User;
import com.sample.app.request.CreateUserRequest;
import com.sample.app.request.GetUserRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.response.GetUserResponse;
import com.sample.app.service.IUserService;

@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UMSServiceObjectMapper mapper;

	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {

		log.info("calling create user api for request : " + request);

		CreateUserResponse response = new CreateUserResponse();

		List<User> users = userDao.findByEmail(request.getUserDto().getEmail());
		if (users.size() > 0) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_ALREADY_EXISTS.errMsg(),
					UMSGenericExceptionCodes.EMAIL_ALREADY_EXISTS.errMsg());
		} else {

			User user = mapper.mapUserDTOtoUser(request.getUserDto());
			user.setActive(true);
			user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			user.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
			userDao.save(user);
			UserDTO userDto = mapper.mapUserToUserDto(user);
			response.setUserDto(userDto);
			response.setToken("To be Implemented");
		}

		return response;
	}

	@Override
	public GetUserResponse getUserByEmail(GetUserRequest request) {
		List<User> user = userDao.findByEmail(request.getEmail());
		if (user == null || user.isEmpty()) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errCode(),
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errMsg());
		}
		UserDTO userDto = mapper.mapUserToUserDto(user.get(0));
		GetUserResponse response = new GetUserResponse();
		response.setUserDto(userDto);
		return response;
	}

}
