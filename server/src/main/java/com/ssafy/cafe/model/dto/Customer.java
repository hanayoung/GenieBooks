package com.ssafy.cafe.model.dto;

import java.util.List;

public class Customer {
	private Integer cId;
	private String id;
	private String nickname;
	private String pwd;
	private Integer age;
	private Sex sex;
	private List<Category> category;

	public Customer() {
	}

	public Customer(String id, String nickname, String pwd, Integer age, Sex sex, List<Category> category) {
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
		this.age = age;
		this.sex = sex;
		this.category = category;
	}

	public Customer(Integer cId, String id, String nickname, String pwd, Integer age, Sex sex, List<Category> category) {
		super();
		this.cId = cId;
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
		this.age = age;
		this.sex = sex;
		this.category = category;
	}

	public Integer getCId() {
		return cId;
	}

	public void setCId(Integer cId) {
		this.cId = cId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", id=" + id + ", nickname=" + nickname + ", pwd=" + pwd + ", age=" + age
				+ ", sex=" + sex + ", category= "+ getCategory().size()+ "]";
	}

}
