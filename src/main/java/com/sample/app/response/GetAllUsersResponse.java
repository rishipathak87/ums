package com.sample.app.response;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.app.dto.UserDTO;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GetAllUsersResponse {
	
	private List <UserDTO> userDtoList;
	private String status;
	
}
