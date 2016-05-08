package com.sample.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dao.UserDao;
import com.sample.app.errorcodes.UMSRequestExceptionCodes;
import com.sample.app.exception.RequestParameterException;
import com.sample.app.request.CreateUserRequest;
import com.sample.app.request.ForgotPasswordRequest;
import com.sample.app.request.GetUserRequest;
import com.sample.app.request.ResetPasswordRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.response.DeleteUserResponse;
import com.sample.app.response.ForgotPasswordResponse;
import com.sample.app.response.GetAllUsersResponse;
import com.sample.app.response.GetUserResponse;
import com.sample.app.response.ResetPasswordResponse;
import com.sample.app.service.impl.UserService;
import com.sample.app.validator.PasswordChangeRequestValidator;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/health")
	public String healthCheck() {
		return "200 OK!";
	}

	// Create User API
	@RequestMapping(method = RequestMethod.POST, value = "/email")
	public CreateUserResponse createUser(
			@RequestBody @Valid CreateUserRequest request,
			BindingResult results, HttpServletRequest httpRequest) throws Exception {
		if (results.hasErrors() && null != results.getAllErrors()) {
			UMSRequestExceptionCodes code = UMSRequestExceptionCodes
					.valueOf(results.getAllErrors().get(0).getDefaultMessage());
			log.error("Invalid Request Error occured while  creating user");
			throw new RequestParameterException(code.errCode(), code.errMsg());
		}
		return userService.createUser(request);
	}

	// Get User Details By Email API
	@RequestMapping(method = RequestMethod.GET, value = "/email")
	public GetUserResponse getUserByEmail(GetUserRequest request) throws Exception {
		return userService.getUserByEmail(request);
	}

	// Get Passowrd By Email API
	@RequestMapping(method = RequestMethod.POST, value = "/forgot/password")
	public ForgotPasswordResponse forgotPassword(
			ForgotPasswordRequest request) {
		return userService.forgotPassword("email");
	}

	@RequestMapping(method = RequestMethod.GET, value = "delete")
	public DeleteUserResponse deleteUserByEmail(
			@RequestParam("email") @NotNull String email,
			@RequestParam(value = "token", required = false) String token) {
		return userService.deleteUserByEmail(email);
	}

	// This API will take Email as Input and will retrieve all users List only
	// if
	// User role is Admin
	@RequestMapping(method = RequestMethod.GET, value = "users")
	public GetAllUsersResponse getAllUsers(
			@RequestParam("email") @NotNull String email,
			@RequestParam("pagination") @NotNull int pagination) {
		return userService.getAllUsers(email, pagination);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "changePassword")
	public ResetPasswordResponse changePassword(
			@RequestBody ResetPasswordRequest request) {

		log.info("calling change password api for request : " + request);

		ResetPasswordResponse response = new ResetPasswordResponse();

		if (!PasswordChangeRequestValidator.validatePasswordChangeRequest(
				request).equals("Valid Request")) {
			response.setErrorCode("EG-011");
			response.setErrorMessage(PasswordChangeRequestValidator
					.validatePasswordChangeRequest(request));
			return response;
		} else

			return userService.changePassword(request);

	}

	// // PassWord Change API
	// @RequestMapping(method = RequestMethod.PUT, value =
	// "/api/v1/user/changePassword")
	// public Map<String, Object> changePassword(
	// @RequestBody Map<String, Object> userMap,
	// @RequestParam("email") String email) {
	//
	// Map<String, Object> response = new LinkedHashMap<String, Object>();
	//
	// if (!userMap.containsKey("password")) {
	// response.put("message", "Password is a Mandatory Param");
	// } else if (userMap.size() == 0) {
	// response.put("message", "Password is a Mandatory Param");
	// } else {
	// if (getUserDetails(email).size() == 0) {
	// response.put("message",
	// "No Account Exists for the Requested Email");
	// } else {
	// User user = getUserDetails(email).get(0);
	// User p = new User();
	//
	// p.setPassword(userMap.get("password").toString());
	//
	// userDao.delete(user);
	// userDao.save(p);
	//
	// response.put("message",
	// "Password have been successfully changed. Thanks!");
	// response.put("user", p);
	// }
	// }
	// return response;
	// }
	//
	// // Expire Token
	// @RequestMapping(method = RequestMethod.PUT, value =
	// "/api/v1/user/expireToken")
	// public Map<String, Object> changePassword(
	// @RequestParam("email") String email) {
	//
	// Map<String, Object> response = new LinkedHashMap<String, Object>();
	//
	// if (getUserDetails(email).size() == 0) {
	// response.put("message", "No Account Exists for the Requested Email");
	// } else {
	// User user = getUserDetails(email).get(0);
	// User p = new User();
	//
	// userDao.delete(user);
	// userDao.save(p);
	//
	// response.put("message",
	// "Token has been successfully Expired. Please signIn once again. Thanks!");
	// response.put("user", p);
	// }
	// return response;
	// }
	//
	// // Sign In
	// @RequestMapping(method = RequestMethod.POST, value =
	// "/api/v1/user/signIn")
	// public Map<String, Object> signIn(@RequestParam("email") String email,
	// @RequestParam("password") String password) {
	//
	// Map<String, Object> response = new LinkedHashMap<String, Object>();
	//
	// if (getUserDetails(email).size() == 0) {
	// response.put("message", "No Account Exists for the Requested Email");
	// } else if (StringUtils.isEmpty(password)) {
	// response.put("message", "Please provide Password");
	// } else {
	// User user = getUserDetails(email).get(0);
	// GetUserRequest getUserRequest = new GetUserRequest();
	// getUserRequest.setEmail(email);
	// String actualPass = userService.getUserByEmail(getUserRequest)
	// .getUserDto().getPassword();
	//
	// if (!actualPass.equals(password)) {
	// response.put("message",
	// "Password Does not Match. Please try again"
	// + " with the right Password");
	// } else {
	//
	// User p = new User();
	//
	// userDao.delete(user);
	// userDao.save(p);
	//
	// response.put("message", "Signed In Successfully. Thanks!");
	// response.put("user", p);
	// }
	// }
	// return response;
	// }

}
