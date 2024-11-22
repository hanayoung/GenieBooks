package com.ssafy.cafe.model.dto;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private Integer id;
	private Integer userId;
	private Date orderTime;
	private Boolean completed;
	private List<OrderDetailInfo> details ;

	public OrderInfo(Integer id, Integer userId, Date orderTime, Boolean completed) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.completed = completed;
    }

    public OrderInfo(Integer userId, Date orderTime, Boolean completed) {
        this.userId = userId;
        this.orderTime = orderTime;
        this.completed = completed;
    }
    
	public OrderInfo() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public List<OrderDetailInfo> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailInfo> details) {
		this.details = details;
	}


	@Override
	public String toString() {
		return "OrderInfo{" +
				"id=" + id +
				", userId=" + userId +
				", orderTime=" + orderTime +
				", completed=" + completed +
				", details=" + details +
				'}';
	}
}
