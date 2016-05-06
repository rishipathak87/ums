package com.sample.app.mapper;

import org.springframework.stereotype.Component;

import com.sample.app.dto.UserDTO;
import com.sample.app.enums.Role;
import com.sample.app.model.User;

@Component
public class UMSServiceObjectMapper {

	public User mapUserDTOtoUser(UserDTO userDTo) {
		User user = new User();
		user.setDisplayName(userDTo.getDisplayName());
		user.setDob(userDTo.getDob());
		user.setEmail(userDTo.getEmail());
		user.setFirstName(userDTo.getFirstName());
		user.setGender(userDTo.getGender());
		user.setLastName(userDTo.getLastName());
		user.setMiddleName(userDTo.getMiddleName());
		user.setPassword(userDTo.getPassword());
		if (userDTo.getRole() == null) {
			user.setRole(Role.STANDARD.toString());
		} else {
			user.setRole(userDTo.getRole().toString());
		}
		user.setMobileNumber(userDTo.getMobileNumber());
		return user;
	}

	public UserDTO mapUserToUserDto(User user) {

		UserDTO dto = new UserDTO();
		dto.setIsActive(String.valueOf(user.isActive()));
		dto.setDisplayName(user.getDisplayName());
		dto.setDob(user.getDob());
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setGender(user.getGender());
		dto.setLastName(user.getLastName());
		dto.setMiddleName(user.getMiddleName());
		dto.setMobileNumber(user.getMobileNumber());
		return dto;

	}

}
