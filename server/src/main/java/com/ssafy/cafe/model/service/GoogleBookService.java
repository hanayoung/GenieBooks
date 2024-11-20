package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.dto.User;


public interface GoogleBookService {
    /**
     * 사용자 정보를 DB에 저장한다.
     * 
     * @param user
     */
    public List<GoogleBook> selectBooksbyCategory(String category);

    
    
}
