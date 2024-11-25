package com.ssafy.cafe.model.dto;

import java.util.Date;

public class GiftCard {
	private int id;
	private String title;
	private String content;
	private String imgUrl;
	private Date giftDate;
	private String senderName;
	private int senderId;
	private int recipientId;
	
	public GiftCard() {}
	
	public GiftCard(String title, String content, String imgUrl, Date giftDate, String senderName, int senderId) {
		super();
		this.title = title;
		this.content = content;
		this.imgUrl = imgUrl;
		this.giftDate = giftDate;
		this.senderId = senderId;
	}

	public GiftCard(int id, String title, String content, String imgUrl, Date giftDate, String senderName, int senderId,
			int recipientId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imgUrl = imgUrl;
		this.giftDate = giftDate;
		this.senderName = senderName;
		this.senderId = senderId;
		this.recipientId = recipientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getGiftDate() {
		return giftDate;
	}

	public void setGiftDate(Date giftDate) {
		this.giftDate = giftDate;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	@Override
	public String toString() {
		return "GiftCard [id=" + id + ", title=" + title + ", content=" + content + ", imgUrl=" + imgUrl + ", giftDate="
				+ giftDate + ", senderId=" + senderId + ", recipientId=" + recipientId + "]";
	}


	

	
	
}
