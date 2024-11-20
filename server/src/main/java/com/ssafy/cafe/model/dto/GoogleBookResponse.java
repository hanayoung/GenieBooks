package com.ssafy.cafe.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookResponse {
	String kind;
	int totalItems;
	List<GoogleBook> items;
	
	public GoogleBookResponse() {}
	
	public GoogleBookResponse(String kind, int totalItems, List<GoogleBook> items) {
		super();
		this.kind = kind;
		this.totalItems = totalItems;
		this.items = items;
	}
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public List<GoogleBook> getItems() {
		return items;
	}
	public void setItems(List<GoogleBook> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GoogleBookResponse [kind=" + kind + ", totalItems=" + totalItems + ", items=" + items + "]";
	}
	
	
	
}
