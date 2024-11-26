package com.ssafy.cafe.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.cafe.model.dto.BookRecommendation;

public class BookParser {

    public static List<BookRecommendation> parseBookRecommendations(String content) {
        List<BookRecommendation> books = new ArrayList<>();

        // 책 정보를 개별적으로 분리
        String[] entries = content.trim().split("\n\n");

        for (String entry : entries) {
            BookRecommendation book = new BookRecommendation();

            // 각 줄을 분리하여 key-value 형태로 저장
            String[] lines = entry.split("\n");
            for (String line : lines) {
                String[] keyValue = line.split(": ", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().toLowerCase();
                    String value = keyValue[1].trim().replaceAll("\\[|\\]", ""); // 대괄호 제거

                    switch (key) {
                        case "title":
                            book.setTitle(value);
                            break;
                        case "author":
                            book.setAuthor(value);
                            break;
                        case "reason":
                            book.setReason(value);
                            break;
                    }
                }
            }

            books.add(book);
        }

        return books;
    }
}
