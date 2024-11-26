package com.ssafy.cafe.model.dto;

import java.util.List;

public class BookRecommendRequest {
    private List<Category> interests;
    private int age;
    private Sex sex;
    
    public BookRecommendRequest() {}
    
	public BookRecommendRequest(List<Category> interests, int age, Sex sex) {
		super();
		this.interests = interests;
		this.age = age;
		this.sex = sex;
	}
	public List<Category> getInterests() {
		return interests;
	}
	public void setInterests(List<Category> interests) {
		this.interests = interests;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	

	@Override
	public String toString() {
		return "Message [interests=" + interests + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
