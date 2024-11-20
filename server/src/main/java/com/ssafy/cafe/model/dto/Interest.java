package com.ssafy.cafe.model.dto;

public class Interest {
	private Integer iId;
	private Integer cId;
    private Category category;
    
    
	public Interest(Integer iId, Integer cId, Category category) {
		super();
		this.iId = iId;
		this.cId = cId;
		this.category = category;
	}
	
	public Interest(Integer cId, Category category) {
		this.cId = cId;
		this.category = category;
	}
	
	public Integer getIId() {
		return iId;
	}
	public void setIId(Integer iId) {
		this.iId = iId;
	}
	public Integer getCId() {
		return cId;
	}
	public void setCId(Integer cId) {
		this.cId = cId;
	}
	public Category getType() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
    
	
}
