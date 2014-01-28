package com.prospring3.ch14.domain;

public enum CustomerType {

	INDIVIDUAL("I"), CORPORATE("C");

	private String code;

	private CustomerType(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CustomerType [code=" + code + "]";
	}

}
