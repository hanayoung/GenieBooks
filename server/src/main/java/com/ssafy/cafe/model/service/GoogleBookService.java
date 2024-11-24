package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.dto.GoogleBookResponse;
import com.ssafy.cafe.model.dto.User;


public interface GoogleBookService {
    public List<GoogleBook> selectBooksbyCategory(String[] categoryList);

    public List<GoogleBook> selectRecommendBooks();

    public List<Long> selectRecommendIsbn();
    
    public GoogleBook selectBookbyId(String id);
    
}
