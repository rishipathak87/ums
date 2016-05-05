package com.sample.app.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.*;

import com.sample.app.dao.UserDao;
import com.sample.app.model.User;
import com.sample.app.request.CreateUserRequest;
import com.sample.app.response.CreateUserResponse;
import com.sample.app.service.IUserService;

@Service
@Slf4j
public class UserService implements IUserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {

		log.info("calling create user api for request : " + request);

	//	Map<String, Object> response = new LinkedHashMap<String, Object>();
		CreateUserResponse response = new CreateUserResponse();
		
		User person = new User();
		
		try{
			if (StringUtils.isEmpty(request.getFirstName())) {
				response.setErrorCode("ER-005"); 
				response.setErrorMessage("Please enter a valid First Name");
			} else if (StringUtils.isEmpty(request.getMiddleName())) {
				response.setErrorCode("ER-005"); 
				response.setErrorMessage("Please enter a valid Middle Name");
			} else if (StringUtils.isEmpty(request.getLastName())) {
				response.setErrorCode("ER-005"); 
				response.setErrorMessage("Please enter a valid Last Name");
			} else if (StringUtils.isEmpty(request.getDisplayName())) {
				response.setErrorCode("ER-005"); 
				response.setErrorMessage("Please enter a valid Display Name");
			} else if (StringUtils.isEmpty(request.getEmail())) {
				response.setErrorCode("ER-008"); 
				response.setErrorMessage("Please enter your email address.");
			}  else if (StringUtils.isEmpty(request.getPassword())) {
				response.setErrorCode("ER-003"); 
				response.setErrorMessage("Please enter your password");
			} else if (request.getPassword().length() < 6) {
				response.setErrorCode("ER-001"); 
				response.setErrorMessage("Please enter a valid passowrd with 6 digits");
			} else if (StringUtils.isEmpty(request.getGender())) {
				response.setErrorCode("ER-006"); 
				response.setErrorMessage("Please select a valid gender");
			}  else if (!((request.getGender().equalsIgnoreCase("male")) || 
					(request.getGender().equalsIgnoreCase("female")))) {
				response.setErrorCode("ER-006"); 
				response.setErrorMessage("Please select a valid gender - male/female");
			}  
			else if (StringUtils.isEmpty(request.getDob())) {
				response.setErrorCode("ER-007"); 
				response.setErrorMessage("Please select a valid date of birth in format yyyy-mm-dd");
			} else if (StringUtils.isEmpty(request.getMobileNumber())) {
				response.setErrorCode("ER-003"); 
				response.setErrorMessage("Please enter your Mobile Number");
			} else {

				List<User> list = userDao.findByEmail(request.getEmail());
				if (list.size() > 0) {
					response.setErrorCode("ER-004"); 
					response.setErrorMessage("This email is already registered with other user, please enter a different email address");
				} else {
					
					person.setActive(true);
					person.setCreatedTime(new Timestamp(System.currentTimeMillis()));
					person.setDisplayName(request.getDisplayName());
					person.setDob(request.getDob());
					person.setEmail(request.getEmail());
					person.setFirstName(request.getFirstName());
					person.setGender(request.getGender());
					person.setLastName(request.getLastName());
					person.setMiddleName(request.getMiddleName());
					person.setPassword(request.getPassword());
					if(StringUtils.isEmpty(request.getRole())){
						person.setRole("User");
					}else{
						person.setRole(request.getRole());
					}
					person.setRole(request.getRole());
					person.setMobileNumber(request.getMobileNumber());
					person.setCreatedTime(new Timestamp(System.currentTimeMillis()));
					person.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
					userDao.save(person);
					
					response.setActive(person.isActive());
					response.setDisplayName(person.getDisplayName());
					response.setDob(person.getDob());
					response.setEmail(person.getEmail());
					response.setFirstName(person.getFirstName());
					response.setGender(person.getGender());
					response.setLastName(person.getLastName());
					response.setMiddleName(person.getMiddleName());
					response.setMobileNumber(person.getMobileNumber());
					response.setCreatedTime((Date) person.getCreatedTime());
					response.setUpdatedTime((Date) person.getUpdatedTime());
					response.setToken("To be Implemented");
					
				}
			}

		} catch (Exception e) {
			log.info(e.getStackTrace().toString());
			response.setErrorCode("UMS-500");
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
		
	
}
