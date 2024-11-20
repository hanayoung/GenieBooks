package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.GoogleBookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/book")
@CrossOrigin("*")
public class BookRestController {
	
    private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);
    
    @Autowired
    GoogleBookService gService;
    
    @GetMapping("/search")
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. "
    	, description = "<pre> json중에서 cId는 빼고 입력해도 정상동작한다. \n"
    			+ "아래는 aa12 사용자를 추가하는 샘플코드\n "
    			+ "{\r\n"
    			+ "  \"id\": \"aa12\",\r\n"
    			+ "  \"name\": \"aa12\",\r\n"
    			+ "  \"pass\": \"aa12\",\r\n"
    			+ "}"
    			+ "</pre>")
    public List<GoogleBook> selectBooksbyCategory(String category) {
    	logger.debug("book.search category : {}", category);
    	try {
    		Category categoryName = Category.fromString(category);
    		logger.debug("category string : {}",categoryName.getName());
    		return gService.selectBooksbyCategory(categoryName.getName());
    	}catch(Exception e) {
    		return new ArrayList<>();
    	}
    	
    }


}
