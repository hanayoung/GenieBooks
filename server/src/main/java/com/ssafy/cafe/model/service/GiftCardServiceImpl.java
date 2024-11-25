package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.GiftCardDao;
import com.ssafy.cafe.model.dto.GiftCard;

@Service
public class GiftCardServiceImpl implements GiftCardService{

	@Autowired
	private GiftCardDao giftCardDao;
	
	@Override
	public int insertGiftCard(GiftCard card) {
		return giftCardDao.insertGiftCard(card);
	}

	@Override
	public List<GiftCard> selectAllGiftCards(int userId) {
		return giftCardDao.selectAllGiftCards(userId);
	}

	@Override
	public int updateGiftCard(int recipientId, int giftCardId) {
		return giftCardDao.updateGiftCard(recipientId, giftCardId);
	}

}
