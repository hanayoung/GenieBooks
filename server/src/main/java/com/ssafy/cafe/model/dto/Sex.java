package com.ssafy.cafe.model.dto;

public enum Sex {
	WOMAN("여성"), MAN("남성");
	
	private final String name;
	
	private Sex(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
