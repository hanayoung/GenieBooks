package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Operation(summary = "검색한 카테고리에 해당하는 책 목록을 반환한다. 없는 카테고리일 경우 빈 리스트를 반환한다")
    public List<GoogleBook> selectBooksByCategory(String category) {
    	logger.debug("book.search category : {}", category);
    	try {
    		Category categoryName = Category.fromString(category);
    		String[] categoryList = categoryName.getName().split("\\+");
			List<GoogleBook> googleBooks= gService.selectBooksbyCategory(categoryList);
    		return googleBooks.stream()
					.filter(book -> book.getSaleInfo().getListPrice() != null)
					.collect(Collectors.toList());
    	}catch(Exception e) {
    		logger.debug(e.getMessage());
			return new ArrayList<>();
		}
    }

	@GetMapping("/recommend")
	@Operation(summary = "추천 책 목록을 반환한다. 없는 카테고리일 경우 빈 리스트를 반환한다")
	public List<GoogleBook> selectRecommendBooks() {
		try {
			logger.debug("selectRecommendBooks");
			return gService.selectRecommendBooks();
		}catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	@GetMapping("/detail")
	@Operation(summary = "id에 해당하는 책을 반환한다.")
	public GoogleBook selectBookById(String id) {
		logger.debug("id : {}", id);
		try {
			return gService.selectBookbyId(id);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return null;
	}

	@GetMapping("/searchByKeyword")
	@Operation(summary = "검색한 키워드에 해당하는 책 목록을 반환한다. 없는 키워드일 경우 빈 리스트를 반환한다")
	public List<GoogleBook> searchBooksByKeyword(String keyword) {
		logger.debug("book.search keyword : {}", keyword);
    	try {
			List<GoogleBook> googleBooks= gService.searchBooksByKeyword(keyword);
    		return googleBooks.stream()
					.filter(book -> book.getSaleInfo().getListPrice() != null)
					.collect(Collectors.toList());
    	}catch(Exception e) {
    		logger.debug(e.getMessage());
			return new ArrayList<>();
		}
	}
}
