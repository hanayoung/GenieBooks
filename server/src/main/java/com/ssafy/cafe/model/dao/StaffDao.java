package com.ssafy.cafe.model.dao;

import java.util.List;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.Staff;

public interface StaffDao {
	 /**
     * 사용자 정보를 추가한다.
     * @param user
     * @return
     */
    int insert(Staff user);

//    /**
//     * 사용자의 Stamp 정보를 수정한다.
//     * @param user
//     * @return
//     */
//    int updateStamp(User user);
    
    /**
     * 사용자 정보를 조회한다.
     * @param userId
     * @return
     */

    Staff selectById(String userId);

    /**
     * 사용자 정보를 조회한다.
     * @param user
     * @return
     */
    Staff selectByUser(Staff user);
    
    List<Order> selectAllWaitingOrders();
    
    boolean updateOrderState(int orderId);
}
