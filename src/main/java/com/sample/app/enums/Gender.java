package com.sample.app.enums;

import lombok.Getter;

public enum Gender {

	MALE("MALE"), FEMALE("FEMALE"),

	;

	@Getter
	private String Gender;

	private Gender(String gender) {
		this.Gender = gender;
	}

	public static Gender forName(String Gender) {
		if (Gender != null) {
			for (Gender r : values()) {
				if (r.getGender().equals(Gender)) {
					return r;
				}
			}
		}
		return null;
	}

}
