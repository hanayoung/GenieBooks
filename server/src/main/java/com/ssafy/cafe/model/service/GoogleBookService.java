package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.GoogleBook;


public interface GoogleBookService {
    public List<GoogleBook> selectBooksbyCategory(String[] categoryList);

    public List<GoogleBook> selectRecommendBooks();

    public List<Long> selectRecommendIsbn();
    
    public GoogleBook selectBookbyId(String id);
    
    public List<GoogleBook> searchBooksByKeyword(String keyword);
}
