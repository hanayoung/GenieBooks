package com.ssafy.cafe.model.dto;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private Integer userId;
    private Date orderTime;
	private Date pickupTime;
	private Boolean isPickup;
	private Boolean isDone;
	private Integer payment; // 결제된 최종금액
    private String repImgUrl;
    private String repBookTitle;
    private List<OrderDetail> details;

	public Order(Integer id, Integer userId, Date orderTime, Date pickupTime, Boolean isPickup, Boolean isDone, Integer payment, String repImgUrl, String repBookTitle, List<OrderDetail> details) {
		this.id = id;
		this.userId = userId;
		this.orderTime = orderTime;
		this.pickupTime = pickupTime;
		this.isPickup = isPickup;
		this.isDone = isDone;
		this.payment = payment;
		this.repImgUrl = repImgUrl;
		this.repBookTitle = repBookTitle;
		this.details = details;
	}

	public Order(Integer id, Integer userId, Date orderTime, Boolean isPickup, Boolean isDone, Integer payment) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
		this.isPickup = isPickup;
        this.isDone = isDone;
		this.payment = payment;
    }

    public Order(Integer userId, Date orderTime, Boolean isPickup, Boolean isDone, Integer payment) {
        this.userId = userId;
        this.orderTime = orderTime;
		this.isPickup = isPickup;
		this.isDone = isDone;
		this.payment = payment;
    }

	public Order(Integer id, Integer userId, Date orderTime, Boolean isPickup, Boolean isDone) {
		this.id = id;
		this.userId = userId;
		this.orderTime = orderTime;
		this.isPickup = isPickup;
		this.isDone = isDone;
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

	public Boolean getPickup() {
		return isPickup;
	}

	public void setPickup(Boolean isPickup) {
		isPickup = isPickup;
	}

	public Boolean getDone() {
		return isDone;
	}

	public void setDone(Boolean isDone) {
		isDone = isDone;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	
	public String getRepImgUrl() {
		return repImgUrl;
	}

	public void setRepImgUrl(String repImgUrl) {
		this.repImgUrl = repImgUrl;
	}

	public String getRepBookTitle() {
		return repBookTitle;
	}

	public void setRepBookTitle(String repBookTitle) {
		this.repBookTitle = repBookTitle;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", userId=" + userId +
				", orderTime=" + orderTime +
				", pickupTime=" + pickupTime +
				", isPickup=" + isPickup +
				", isDone=" + isDone +
				", payment=" + payment +
				", repImgUrl='" + repImgUrl + '\'' +
				", repBookTitle='" + repBookTitle + '\'' +
				", details=" + details +
				'}';
	}
}
