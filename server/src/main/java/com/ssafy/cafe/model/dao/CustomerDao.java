package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Interest;

public interface CustomerDao {
	 /**
     * 사용자 정보를 추가한다.
     * @param user
     * @return
     */
    int insert(Customer user);
    
	 /**
     * 사용자 정보를 추가한다.
     * @param user
     * @return
     */
    int insertInterest(Interest interest);

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

    Customer selectById(String id);

    /**
     * 사용자 정보를 조회한다.
     * @param user
     * @return
     */
    Customer selectByUser(Customer user);
}
