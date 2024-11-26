package com.ssafy.cafe.controller.rest;

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
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.GiftCard;
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
	public Boolean insertGiftCard(int userId) {
		int result = 0;
		try {
	        Customer selected = cService.getAgeAndSex(userId);
	        BookRecommendRequest request = new BookRecommendRequest();
	        request.setAge(selected.getAge());
	        request.setSex(selected.getSex());
	        List<Category> interest = cService.selectInterestByUserId(userId);
	        request.setInterests(interest);
			result = gptService.getBookRecommendations(request);
			logger.debug("result : {}", result);
		} catch (Exception e) {
			logger.debug("error : {}", e.getMessage());
			result = -1;
		}

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
