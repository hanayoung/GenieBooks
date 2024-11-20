package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.cafe.model.dao.OrderDao;
import com.ssafy.cafe.model.dao.OrderDetailDao;
import com.ssafy.cafe.model.dao.StampDao;
import com.ssafy.cafe.model.dao.UserDao;
import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.OrderDetail;
import com.ssafy.cafe.model.dto.OrderDetailInfo;
import com.ssafy.cafe.model.dto.OrderInfo;
import com.ssafy.cafe.model.dto.Stamp;
import com.ssafy.cafe.model.dto.User;

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
    StampDao sDao;
    
    @Autowired
    UserDao uDao;

    @Override
    @Transactional
    public void makeOrder(Order order) {
    	//client에서 들어온 id 는 무시해야 오류생기지 않음.
    	order.setId(null);
    	
        // 주문 및 주문 상세 테이블 저장
        oDao.insert(order);
        List<OrderDetail> details = order.getDetails();
        int quantitySum = 0;
        for(OrderDetail detail: details) {
            detail.setOrderId(order.getId());
            dDao.insert(detail);
            quantitySum += detail.getQuantity();
        }
        // 스템프 정보 저장
        Stamp stamp = new Stamp(order.getUserId(), order.getId(), quantitySum);
        sDao.insert(stamp);
        // 사용자 정보 업데이트
        User user = new User();
        user.setId(order.getUserId());
        user.setStamps(stamp.getQuantity());
        uDao.updateStamp(user);

    }

    @Override
    public List<Order> getOrderByUser(String id) {
        return oDao.selectByUser(id);
    }

    @Override
    public void updateOrder(Order order) {
        oDao.update(order);
    }

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
    
    @Override
    public List<OrderInfo> getLast6MonthOrder(String id) {
        return oDao.getLast6MonthOrder(id);
    }
}
