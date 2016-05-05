package com.sample.app.enums;

import lombok.Getter;

public enum Role {

	STANDARD("STANDARD"), MENTOR("MENTOR"), GUEST("GUEST"), ADMIN("ADMIN"),

	;

	@Getter
	private String role;

	private Role(String errCode) {
		this.role = errCode;
	}

	public static Role forName(String role) {
		if (role != null) {
			for (Role r : values()) {
				if (r.getRole().equals(role)) {
					return r;
				}
			}
		}
		return null;
	}

}
