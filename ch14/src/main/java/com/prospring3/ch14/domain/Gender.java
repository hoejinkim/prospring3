package com.prospring3.ch14.domain;

public enum Gender {

	MALE("M"), FEMALE("F");

	private String code;

	private Gender(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Gender [code=" + code + "]";
	}

}
