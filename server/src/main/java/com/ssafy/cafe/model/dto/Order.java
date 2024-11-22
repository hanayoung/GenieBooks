package com.ssafy.cafe.model.dto;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private Integer userId;
    private Date orderTime;
    private Boolean completed;
	private Integer payment; // 결제된 최종금액
    private List<OrderDetail> details ;

    public Order(Integer id, Integer userId, Date orderTime, Boolean completed, Integer payment) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.completed = completed;
		this.payment = payment;
    }

    public Order(Integer userId, Date orderTime, Boolean completed, Integer payment) {
        this.userId = userId;
        this.orderTime = orderTime;
        this.completed = completed;
		this.payment = payment;
    }

	public Order(Integer id, Integer userId, Date orderTime, Boolean completed) {
		this.id = id;
		this.userId = userId;
		this.orderTime = orderTime;
		this.completed = completed;
	}

    public Order() {}
        
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

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderTime=" + orderTime
				+ ", completed=" + completed + ", details=" + details + "]";
	}
    
    
}
