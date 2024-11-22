package com.ssafy.cafe.model.dto;

public class OrderDetail {

	private Integer orderId;
	private Long isbn;
    private Integer quantity;
    
    public OrderDetail(Long isbn, Integer orderId, Integer quantity) {
        super();
        this.isbn = isbn;
        this.orderId = orderId;
        this.quantity = quantity;
    }
	public OrderDetail(Long isbn, Integer quantity) {
		super();
		this.isbn = isbn;
		this.quantity = quantity;
	}

    public OrderDetail() {}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
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

	@Override
	public String toString() {
		return "OrderDetail [isbn=" + isbn + ", orderId=" + orderId + ", quantity=" + quantity
				+ "]";
	}
    
    
}
