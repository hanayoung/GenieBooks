package com.ssafy.cafe.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.ssafy.cafe.model.dto.BookRecommendRequest;
import com.ssafy.cafe.model.dto.BookRecommendation;
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.GiftCard;
import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.service.BookParser;
import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.GPTService;
import com.ssafy.cafe.model.service.GiftCardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/rest/recommend")
@CrossOrigin("*")
public class RecommendRestController {

	private static final Logger logger = LoggerFactory.getLogger(RecommendRestController.class);

	@Autowired
	private GPTService gptService;
	
	@Autowired
	private CustomerService cService;
	
	@GetMapping
	@Operation(summary = "추천 서적 반환")
	public List<GoogleBook> insertGiftCard(int userId) {
		try {
	        Customer selected = cService.getAgeAndSex(userId);
	        BookRecommendRequest request = new BookRecommendRequest();
	        request.setAge(selected.getAge());
	        request.setSex(selected.getSex());
	        logger.debug("request sex : {}",selected.getSex());
	        List<Category> interest = cService.selectInterestByUserId(userId);
	        request.setInterests(interest);
	        logger.debug("request : {}",request);
			String recommendBooksString = gptService.getBookRecommendations(request);
			List<BookRecommendation> recommendBooks = BookParser.parseBookRecommendations(recommendBooksString);
			logger.debug("result : {}", recommendBooksString);
			logger.debug("books : {}",Arrays.toString(recommendBooks.toArray()));
		} catch (Exception e) {
			logger.debug("error : {}", e.getMessage());
		}

		return new ArrayList<>();
	}

}
