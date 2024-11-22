package com.ssafy.cafe.model.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ssafy.cafe.controller.rest.CommentRestController;
import com.ssafy.cafe.model.dao.*;
import com.ssafy.cafe.model.dto.*;
import com.ssafy.cafe.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @since 2021. 6. 23.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    OrderDao oDao;
    
    @Autowired
    OrderDetailDao dDao;

    @Autowired
    CustomerDao cDao;

    @Override
    @Transactional
    public void makeOrder(Order order) {
    	//client에서 들어온 id 는 무시해야 오류생기지 않음.
    	order.setId(null);
    	
        // 주문 및 주문 상세 테이블 저장
        oDao.insert(order);
        List<OrderDetail> details = order.getDetails();
//        int quantitySum = 0;
        for(OrderDetail detail: details) {
            detail.setOrderId(order.getId());
            dDao.insert(detail);
//            quantitySum += detail.getQuantity();
        }

        // 포인트 정보 저장 --- > 결제금액 어떻게 알지?
        cDao.updatePoint(order.getUserId(), (int)Math.round(order.getPayment()*0.05));
    }

    @Override
    public List<Order> getOrderByUser(Integer id) {
        return oDao.selectByUser(id);
    }

    @Override
    public List<Order> getOrderInfoByUser(Integer id) {
        List<Order> noDetailOrders = oDao.selectByUser(id);
        List<Order> orderInfos = new ArrayList<>();
        for (Order order : noDetailOrders) {
            orderInfos.add(oDao.selectOrderDetails(order.getId()));
        }
        return orderInfos;
    }

//    @Override
//    public void updateOrder(Order order) {
//        oDao.update(order);
//    }

    @Override
    public OrderInfo getOrderInfo(Integer id) {
    	OrderInfo info = oDao.selectOrderInfo(id);
    	logger.debug("getOrderInfo before detail : {}",info);
    	List<OrderDetailInfo> detailInfo = oDao.getOrderDetailInfo(info.getId());
    	// 이제 googlebook 정보 넣기
    	for(OrderDetailInfo detail : detailInfo) {
    		detail.setGoogleBook(selectByIsbn(detail.getIsbn()));
    		if(detail.getGoogleBook().getSaleInfo().getListPrice() != null) {
    			detail.setSumPrice(detail.getGoogleBook().getSaleInfo().getListPrice().getAmount() * detail.getQuantity());
    		}
    		
    	}
    	
    	info.setDetails(detailInfo);
    	logger.debug("getOrderInfo : {}",info);
        return info;
    }

    public GoogleBook selectByIsbn(Long isbn) {
 	   RestTemplate restTemplate = new RestTemplate();
 	   GoogleBook book = new GoogleBook();
        try{
                URI uri = UriComponentsBuilder
                        .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                        .queryParam("q","isbn:"+isbn)
                        .queryParam("maxResults", 40)
                        .encode()
                        .build()
                        .toUri();
                ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                
                if(response == null || response.getBody().getItems() == null) {
                    uri = UriComponentsBuilder
                            .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                            .queryParam("q",String.format("ISBN:\"%d\"", isbn))
                            .encode()
                            .build()
                            .toUri();
                    response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                }
                
                book = response.getBody().getItems().get(0);
        }catch (Exception e) {
                logger.debug("exception occur : {}",e.getMessage());
        }

        return book;
    }


}
