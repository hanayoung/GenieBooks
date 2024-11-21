package com.ssafy.cafe.model.dto;

public enum Category {
	FICTION("FICTION"), 
	COOKING("COOKING"), 
	SCIENCE("SCIENCE"), 
	HEALTH("HEALTH"), 
	HIANDSOC("HISTORY+SOCIAL"), 
	TRAVEL("TRAVEL"), 
	BUANDEC("BUSINESS+ECONOMICS"), 
	CRANDHOANDFI("CRAFTS+HOBIIES+FITNESS"), 
	TEANDEN("TECHNOLOGY+ENGINEERING"), 
	RELIGION("RELIGION"), 
	POANDLI("POETRY+LITERARY"), 
	ARTANDPER("ART+PERFORMING"), 
	HOANDHO("HOUSE+HOME"), 
	COMPUTER("COMPUTER"), 
	HUMAN("HUMAN+PHILOSOPHY+BIOGRAPHY+PSYCHOLOGY"),
	SELFHELF("SELFHELF"),
	POANDSO("POLITICAL+SOCIAL"),
	FOREIGN("FOREIGN");
	
	private final String name;
	
	private Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.name().equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
