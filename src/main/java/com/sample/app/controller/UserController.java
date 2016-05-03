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

import com.sample.app.model.Person;
import com.sample.app.dao.PersonRepository;

@RestController
@RequestMapping("/")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PersonRepository PersonRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/ping")
	public String healthCheck() {
		return "PONG";
	}

	// Register User API
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/users/registerUser")
	public Map<String, Object> createUser(
			@RequestBody Map<String, Object> userMap) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		logger.info("Entering into Register User Method with Request Body :: "
				+ userMap);

		try {
			if (!userMap.containsKey("firstName")) {
				response.put("message", "First Name is a Mandatory Param");
			} else if (!userMap.containsKey("email")) {
				response.put("message", "Email is a Mandatory Param");
			} else if (!userMap.containsKey("password")) {
				response.put("message", "Password is a Mandatory Param");
			} else if (!userMap.containsKey("role")) {
				response.put("message", "User Role is a Mandatory Param");
			} else if (!userMap.containsKey("token")) {
				response.put("message", "Token is a Mandatory Param");
			} else {

				if (!userMap.containsKey("lastName")) {
					userMap.put("lastName", "");
				}
				List<Person> list = getUserDetails(userMap.get("email")
						.toString());
				if (list.size() > 0) {
					response.put("message",
							"You are already Registered in our System. Please Sign In");
				} else {

					Person person = new Person(userMap.get("firstName")
							.toString(), userMap.get("lastName").toString(),
							userMap.get("email").toString(), userMap.get(
									"password").toString(), userMap.get("role")
									.toString(), userMap.get("token")
									.toString(), true);

					PersonRepository.save(person);

					response.put("message",
							"You have been registered successfully. Thanks!");
					response.put("user", person);
				}
			}

		} catch (Exception e) {
			logger.info(e.getStackTrace().toString());
			response.put("message", "Error Occured");
			response.put("error", e.getStackTrace().toString());
		}
		return response;
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/api/v1/user")
	public List<Person> getUserDetails(@RequestParam("email") String email) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		return PersonRepository.findByEmail(email);
	}

	// Get User Details By Email API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user")
	public Map<String, Object> getUserByEmail(
			@RequestParam("email") String email) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();

		List<Person> list = getUserDetails(email);
		if (list.size() == 0) {
			response.put("message",
					"No Registered User corressponding to this Email");
		} else {

			response.put("user", PersonRepository.findByEmail(email).get(0));
		}
		return response;

	}

	// Get Passowrd By Email API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/retrievePassword")
	public Map<String, Object> getPassword(@RequestParam("email") String email) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		List<Person> p = PersonRepository.findByEmail(email);

		if (p.size() == 0) {
			response.put("message", "No Account Exists for this Email Id");
		} else {
			response.put("password", p.get(0).getPassword());
		}

		return response;
	}

	// Get List of Users By First Name API
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/name/{firstName}")
	public List<Person> getUserDetailsByName(
			@PathVariable("firstName") String firstName) {
		return PersonRepository.findByFirstName(firstName);
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
				Person user = getUserDetails(email).get(0);
				Person p = new Person(user.getFirstName(), user.getLastName(),
						user.getEmail(), user.getPassword(), user.getRole(),
						user.getToken(), true);

				p.setPassword(userMap.get("password").toString());

				PersonRepository.delete(user);
				PersonRepository.save(p);

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
			Person user = getUserDetails(email).get(0);
			Person p = new Person(user.getFirstName(), user.getLastName(),
					user.getEmail(), user.getPassword(), user.getRole(),
					user.getToken(), true);

			p.setIsTokenActive(false);

			PersonRepository.delete(user);
			PersonRepository.save(p);

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
			Person user = getUserDetails(email).get(0);
			String actualPass = PersonRepository.findPasswordByEmail(email)
					.getPassword();

			if (!actualPass.equals(password)) {
				response.put("message",
						"Password Does not Match. Please try again"
								+ " with the right Password");
			} else {

				Person p = new Person(user.getFirstName(), user.getLastName(),
						user.getEmail(), user.getPassword(), user.getRole(),
						user.getToken(), true);

				PersonRepository.delete(user);
				PersonRepository.save(p);

				response.put("message", "Signed In Successfully. Thanks!");
				response.put("user", p);
			}
		}
		return response;
	}

}
