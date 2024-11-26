package com.ssafy.cafe.model.dto;

import java.util.List;

public class Customer {
	private Integer cId;
	private String id;
	private String nickname;
	private String pwd;
	private Integer age;
	private Sex sex;
	private Integer point;
	private List<Category> category;
	private String fcmToken;

	public Customer() {
	}

	public Customer(Integer age, Sex sex) {
		this.age = age;
		this.sex = sex;
	}
	public Customer(String id, String pwd, String fcmToken) {
		this.id = id;
		this.pwd = pwd;
		this.fcmToken = fcmToken;
	}
	public Customer(String id, String nickname, String pwd, Integer age, Sex sex, Integer point, List<Category> category) {
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
		this.age = age;
		this.sex = sex;
		this.point = point;
		this.category = category;
	}

	public Customer(Integer cId, String id, String nickname, String pwd, Integer age, Sex sex, Integer point,
			List<Category> category) {
		super();
		this.cId = cId;
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
		this.age = age;
		this.sex = sex;
		this.point = point;
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
	
	public Integer getPoint() {
		return point;
	}
	
	public void setPoint(Integer point) {
		this.point = point;
	}
 
	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", id=" + id + ", nickname=" + nickname + ", pwd=" + pwd + ", age=" + age
				+ ", sex=" + sex + ", point=" + point + ", category=" + category + ", fcmToken=" + fcmToken + "]";
	}
	
	

//	@Override
//	public String toString() {
//		return "Customer [id=" + id + ", nickname=" + nickname + ", pwd=" + pwd + ", age=" + age + ", sex=" + sex
//				+ ", category= " + "]";
//	}

}
