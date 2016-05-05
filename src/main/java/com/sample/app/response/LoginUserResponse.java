package com.sample.app.response;

import lombok.Data;

import com.sample.app.dto.UserDTO;

@Data
public class LoginUserResponse {
	private UserDTO userDto;
	private String token;
}
