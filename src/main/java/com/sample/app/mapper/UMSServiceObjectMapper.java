package com.sample.app.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sample.app.dto.UserDTO;
import com.sample.app.encryption.TokenDto;
import com.sample.app.enums.Gender;
import com.sample.app.enums.Role;
import com.sample.app.model.User;

@Component
public class UMSServiceObjectMapper {

	public User mapUserDTOtoUser(UserDTO userDTO) {
		User user = new User();
		user.setDisplayName(userDTO.getDisplayName());
		user.setDob(userDTO.getDob());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setGender(userDTO.getGender().toString());
		user.setLastName(userDTO.getLastName());
		user.setMiddleName(userDTO.getMiddleName());
		user.setPassword(userDTO.getPassword());
		if (userDTO.getRole() == null) {
			user.setRole(Role.STANDARD.toString());
		} else {
			user.setRole(userDTO.getRole().toString());
		}
		user.setMobileNumber(userDTO.getMobileNumber());
		return user;
	}

	public UserDTO mapUserToUserDto(User user) {
		UserDTO dto = new UserDTO();
		dto.setIsActive(String.valueOf(user.isActive()));
		dto.setDisplayName(user.getDisplayName());
		dto.setDob(user.getDob());
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setGender(Gender.valueOf(user.getGender()));
		dto.setLastName(user.getLastName());
		dto.setMiddleName(user.getMiddleName());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setUserId(user.getUserId());
		dto.setRole(Role.valueOf(user.getRole()));
		return dto;

	}

	public TokenDto createTokenDto(String userId) {
		TokenDto dto = new TokenDto();
		dto.setUserId(userId);
		dto.setUuid(UUID.randomUUID().toString());
		dto.setExpiry(new Date());
		return dto;
	}
}
