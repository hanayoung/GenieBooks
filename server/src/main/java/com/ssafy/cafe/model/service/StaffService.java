package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.Staff;


public interface StaffService {
    /**
     * 사용자 정보를 DB에 저장한다.
     * 
     * @param user
     */
    public int join(Staff staff);

    /**
     * id, pass에 해당하는 User 정보를 반환한다.
     * 
     * @param id
     * @param pass
     * @return
     * 조회된 User 정보를 반환한다.
     */
    public Staff login(String id, String pass);
    
    
    /**
     * 해당 아이디가 이미 사용 중인지를 반환한다.
     * @param id
     * @return
     */
    public boolean isUsedId(String id);

    /**
     * id 에 해당하는 User 정보를 반환한다.
     * 
     * @param id
     * @return
     * 조회된 User 정보를 반환한다.
     */
    public Staff selectUser(String id);
    
    public List<Order> selectAllWaitingOrders();
    
    public boolean updateOrderState(int orderId);
    
    
}
