package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dao.*;
import com.ssafy.cafe.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since 2021. 6. 23.
 */
@Service
public class OrderServiceImpl implements OrderService {

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
    public List<Order> getOrderByUser(String id) {
        return oDao.selectByUser(id);
    }

//    @Override
//    public void updateOrder(Order order) {
//        oDao.update(order);
//    }
//
    @Override
    public OrderInfo getOrderInfo(Integer id) {
        return oDao.selectOrderInfo(id);
    }

    @Override
    public List<OrderInfo> getLastMonthOrder(String id) {
    	List<OrderInfo> info = oDao.getLastMonthOrder(id);
    	for (OrderInfo orderInfo : info) {
        	List<OrderDetailInfo> detailInfo = oDao.getOrderDetailInfo(orderInfo.getId());

        	orderInfo.setDetails(detailInfo);
		}

        return info;
    }
//
//    @Override
//    public List<OrderInfo> getLast6MonthOrder(String id) {
//        return oDao.getLast6MonthOrder(id);
//    }
}
