package com.ssafy.cafe.model.dto;

public enum Category {
	FICTION("FICTION"), 
	COOKING("COOKING"), 
	SCIENCE("SCIENCE+NATURE"),
	HEALTH("MEDICAL"),
	HIANDSOC("HISTORY+SOCIAL SCIENCE"),
	TRAVEL("TRAVEL"),
	CRANDHOANDFI("GARDENING+GAMES+PETS"),
	TEANDEN("TRANSPORTATION+ARCHITECTURE"),
	RELIGION("RELIGION+BIBLES"),
	POANDLI("POETRY+LITERARY COLLECTIONS"),
	ARTANDPER("ART+PERFORMING ARTS"),
	COMPUTER("COMPUTERS"),
	HUMAN("DESIGN+PHILOSOPHY+PSYCHOLOGY"),
	SELFHELF("Self-Help+DESIGN"),
	POANDSO("POLITICAL SCIENCE+SOCIAL SCIENCE+LAW"),
	FOREIGN("FOREIGN LANGUAGE STUDY"),
	JUVENILE("JUVENILE FICTION+");
	
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
