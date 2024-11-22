package com.ssafy.cafe.model.dto;

public class OrderDetailInfo {
	private Long isbn;
	private Integer orderId;
	private Integer quantity;
	private GoogleBook googleBook;
    private int sumPrice; // quantity * 상품가격

	public OrderDetailInfo(Long isbn, Integer orderId, Integer quantity) {
		super();
		this.isbn = isbn;
		this.orderId = orderId;
		this.quantity = quantity;
	}

    public OrderDetailInfo(Long isbn, Integer quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
    }

	public OrderDetailInfo(Long isbn, Integer orderId, Integer quantity, GoogleBook googleBook, int sumPrice) {
		this.isbn = isbn;
		this.orderId = orderId;
		this.quantity = quantity;
		this.googleBook = googleBook;
		this.sumPrice = sumPrice;
	}

	public OrderDetailInfo() {}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public GoogleBook getGoogleBook() {
		return googleBook;
	}

	public void setGoogleBook(GoogleBook googleBook) {
		this.googleBook = googleBook;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

    
}
