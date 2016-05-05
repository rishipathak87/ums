package com.sample.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dao.UserDao;
import com.sample.app.model.User;
import com.sample.app.request.CreateUserRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.service.IUserService;
import com.sample.app.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/ping")
	public String healthCheck() {
		return "PONG";
	}

	// Register User API
	@RequestMapping(method = RequestMethod.POST, value = "email")
	public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/api/v1/user")
	public List<User> getUserDetails(@RequestParam("email") String email) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		return userDao.findByEmail(email);
	}

	// Get User Details By Email API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user")
	public Map<String, Object> getUserByEmail(
			@RequestParam("email") String email) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();

		List<User> list = getUserDetails(email);
		if (list.size() == 0) {
			response.put("message",
					"No Registered User corressponding to this Email");
		} else {

			response.put("user", userDao.findByEmail(email).get(0));
		}
		return response;

	}

	// Get Passowrd By Email API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/retrievePassword")
	public Map<String, Object> getPassword(@RequestParam("email") String email) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		List<User> p = userDao.findByEmail(email);

		if (p.size() == 0) {
			response.put("message", "No Account Exists for this Email Id");
		} else {
			response.put("password", p.get(0).getPassword());
		}

		return response;
	}

	// Get List of Users By First Name API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/name/{firstName}")
	public List<User> getUserDetailsByName(
			@PathVariable("firstName") String firstName) {
		return userDao.findByFirstName(firstName);
	}

	// PassWord Change API
	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/user/changePassword")
	public Map<String, Object> changePassword(
			@RequestBody Map<String, Object> userMap,
			@RequestParam("email") String email) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (!userMap.containsKey("password")) {
			response.put("message", "Password is a Mandatory Param");
		} else if (userMap.size() == 0) {
			response.put("message", "Password is a Mandatory Param");
		} else {
			if (getUserDetails(email).size() == 0) {
				response.put("message",
						"No Account Exists for the Requested Email");
			} else {
				User user = getUserDetails(email).get(0);
				User p = new User();

				p.setPassword(userMap.get("password").toString());

				userDao.delete(user);
				userDao.save(p);

				response.put("message",
						"Password have been successfully changed. Thanks!");
				response.put("user", p);
			}
		}
		return response;
	}

	// Expire Token
	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/user/expireToken")
	public Map<String, Object> changePassword(
			@RequestParam("email") String email) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (getUserDetails(email).size() == 0) {
			response.put("message", "No Account Exists for the Requested Email");
		} else {
			User user = getUserDetails(email).get(0);
			User p = new User();

			userDao.delete(user);
			userDao.save(p);

			response.put("message",
					"Token has been successfully Expired. Please signIn once again. Thanks!");
			response.put("user", p);
		}
		return response;
	}

	// Sign In
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/user/signIn")
	public Map<String, Object> signIn(@RequestParam("email") String email,
			@RequestParam("password") String password) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (getUserDetails(email).size() == 0) {
			response.put("message", "No Account Exists for the Requested Email");
		} else if (StringUtils.isEmpty(password)) {
			response.put("message", "Please provide Password");
		} else {
			User user = getUserDetails(email).get(0);
			String actualPass = userDao.findPasswordByEmail(email)
					.getPassword();

			if (!actualPass.equals(password)) {
				response.put("message",
						"Password Does not Match. Please try again"
								+ " with the right Password");
			} else {

				User p = new User();

				userDao.delete(user);
				userDao.save(p);

				response.put("message", "Signed In Successfully. Thanks!");
				response.put("user", p);
			}
		}
		return response;
	}

}
