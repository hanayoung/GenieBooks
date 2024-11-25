package com.ssafy.cafe.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.cafe.model.dto.GiftCard;

public interface GiftCardDao {
	int insertGiftCard(GiftCard card);
	
	List<GiftCard> selectAllGiftCards(int userId);
	
	int updateGiftCard(@Param("recipient_id")int recipientId, @Param("giftcard_id") int giftCardId);
}
