package com.ssafy.cafe.model.dto;

public class BookRecommendation {
    private String title;
    private String author;
    private String reason;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "BookRecommendation{" +
               "title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", reason='" + reason + '\'' +
               '}';
    }
}
