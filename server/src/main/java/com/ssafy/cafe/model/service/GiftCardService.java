package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.GiftCard;

public interface GiftCardService {

	public int insertGiftCard(GiftCard card);
	
	public List<GiftCard> selectAllGiftCards(int userId);
	
	public int updateGiftCard(int recipientId, int giftCardId);
	
	public GiftCard selectGiftCardById(int giftCardId);
}
