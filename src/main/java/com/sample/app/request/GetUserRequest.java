package com.sample.app.request;

import lombok.Data;

@Data
public class GetUserRequest {
	private String token;
	private String email;
}
