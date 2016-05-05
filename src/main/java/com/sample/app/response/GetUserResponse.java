package com.sample.app.response;

import lombok.Data;

import com.sample.app.dto.UserDTO;

@Data
public class GetUserResponse {
	private UserDTO userDto;
}
