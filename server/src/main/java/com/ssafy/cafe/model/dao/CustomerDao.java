package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Attendance;
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Interest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    int delete(int userId);

    /**
     * 사용자의 Point 정보를 수정한다.
     * @param user
     * @return
     */
    int updatePoint(@Param("user_id") int userId, @Param("point") int point);

    int getUserPoint(int userId);
    
    /**
     * 사용자 정보를 조회한다.
     * @param userId
     * @return
     */

    Customer selectById(String id);

    List<Category> selectInterestByUserId(int id);

    /**
     * 사용자 정보를 조회한다.
     * @param user
     * @return
     */
    Customer selectByUser(Customer user);
    
    int uploadToken(@Param("token") String token, @Param("user_id") int userId);
    
    String getFcmTokenbyUserId(int userId);


}
