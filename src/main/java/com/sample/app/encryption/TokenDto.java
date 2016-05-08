package com.sample.app.encryption;

import java.util.Date;

import lombok.Data;

@Data
public class TokenDto {

	private String userId;
	private String uuid;
	private Date expiry;
	 
}
