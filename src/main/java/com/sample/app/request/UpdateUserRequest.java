package com.sample.app.request;

import java.io.Serializable;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.sample.app.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Valid
	private UserDTO userDTO;

}
