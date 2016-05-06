package com.sample.app.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.sample.app.request.ResetPasswordRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.response.DeleteUserResponse;
import com.sample.app.response.ForgotPasswordResponse;
import com.sample.app.response.GetAllUsersResponse;
import com.sample.app.response.GetUserResponse;
import com.sample.app.response.ResetPasswordResponse;
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

		List<User> users = userDao.findByEmail(request.getUserDTO().getEmail());
		if (users.size() > 0) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_ALREADY_EXISTS.errMsg(),
					UMSGenericExceptionCodes.EMAIL_ALREADY_EXISTS.errMsg());
		} else {

			User user = mapper.mapUserDTOtoUser(request.getUserDTO());
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
	// public GetUserResponse getUserByEmail(GetUserRequest request) {
	public GetUserResponse getUserByEmail(String email) {
		List<User> user = userDao.findByEmail(email);
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

	@Override
	public ForgotPasswordResponse getPassByEmail(String email) {
		List<User> user = userDao.findByEmail(email);
		if (user == null || user.isEmpty()) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errCode(),
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errMsg());
		}
		// UserDTO userDto = mapper.mapUserToUserDto(user.get(0));
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		response.setEmail(user.get(0).getEmail());
		response.setPassword(user.get(0).getPassword());
		return response;
	}

	public DeleteUserResponse deleteUserByEmail(String email) {
		DeleteUserResponse response = new DeleteUserResponse();
		List<User> user = userDao.findByEmail(email);
		if (user == null || user.isEmpty()) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errCode(),
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errMsg());
		}
		user.get(0).setActive(false);
		userDao.save(user.get(0));
		response.setEmail(user.get(0).getEmail());
		response.setStatus("User Account Deleted Successfully");
		return response;
	}

	public GetAllUsersResponse getAllUsers(String email, int pagination) {
		GetAllUsersResponse response = new GetAllUsersResponse();
		int numberOfResults = 10;
		long userCount = userDao.count();
		if (userCount <= pagination * numberOfResults) {
			response.setStatus("Please provide a lesser digit in Pagination Field");
		} else if ((userCount > pagination * numberOfResults) && pagination !=0) {
			List<User> user = userDao.findAll().subList(pagination,
					pagination + numberOfResults);

			if (user == null || user.isEmpty() || user.size() == 0) {
				response.setStatus("Please login with Admin Role to fetch all Users");
			} else {
				UserDTO userDto;
				ArrayList<UserDTO> list = new ArrayList<UserDTO>();
				for (int i = 0; i < user.size(); i++) {
					userDto = mapper.mapUserToUserDto(user.get(i));
					list.add(userDto);
				}
				response.setUserDtoList(list);
			}
		} else if ((pagination == 0)) {
			List<User> user = userDao.findAll().subList(pagination,
					(int) userCount);

			if (user == null || user.isEmpty() || user.size() == 0) {
				response.setStatus("Please login with Admin Role to fetch all Users");
			} else {
				UserDTO userDto;
				ArrayList<UserDTO> list = new ArrayList<UserDTO>();
				for (int i = 0; i < user.size(); i++) {
					userDto = mapper.mapUserToUserDto(user.get(i));
					list.add(userDto);
				}
				response.setUserDtoList(list);
			}
		}
		return response;
	}

	public ResetPasswordResponse changePassword(ResetPasswordRequest request) {

		ResetPasswordResponse response = new ResetPasswordResponse();

		List<User> users = userDao.findByEmail(request.getEmail());
		if (users.size() == 0) {
			throw new UMSGenericException(
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errMsg(),
					UMSGenericExceptionCodes.EMAIL_DOES_NOT_EXISTS.errMsg());
		} else {
			User user = users.get(0);
			user.setPassword(request.getNewPassword());
			user.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
			userDao.save(user);

			response.setStatus("Success");
		}
		return response;
	}
}
