package com.ssafy.cafe.controller.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.GiftCard;
import com.ssafy.cafe.model.service.GiftCardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/rest/gift")
@CrossOrigin("*")
public class GiftCardRestController {
	private static final Logger logger = LoggerFactory.getLogger(GiftCardRestController.class);

	@Autowired
	private GiftCardService giftCardService;

	@PostMapping
	@Operation(summary = "선물카드 추가")
	public Boolean insertGiftCard(@RequestBody GiftCard giftCard) {
		int result = 0;
		try {
			result = giftCardService.insertGiftCard(giftCard);
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

	@GetMapping
	private List<GiftCard> selectAllGiftCards(int userId) {
		return giftCardService.selectAllGiftCards(userId);
	}

	@PutMapping
	private int receiveGiftCard(@RequestBody Map<String, Integer> payload) {
		int recipientId = payload.get("recipientId");
		int giftCardId = payload.get("giftCardId");

		return giftCardService.updateGiftCard(recipientId, giftCardId);
	}

	@GetMapping("selectById")
	private GiftCard selectGiftCardById(int giftCardId) {
		return giftCardService.selectGiftCardById(giftCardId);
	}

}
