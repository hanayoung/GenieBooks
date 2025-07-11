package com.ssafy.cafe.model.service;

import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;

import java.util.List;


public interface CustomerService {
    /**
     * 사용자 정보를 DB에 저장한다.
     * 
     * @param user
     */
    public int join(Customer customer);

    /**
     * id, pass에 해당하는 User 정보를 반환한다.
     * 
     * @param id
     * @param pass
     * @return
     * 조회된 User 정보를 반환한다.
     */
    public Customer login(String id, String pass);
    
    
    /**
     * 해당 아이디가 이미 사용 중인지를 반환한다.
     * @param id
     * @return
     */
    public boolean isUsedId(String id);


    public void delete(int id);

    /**
     * id 에 해당하는 User 정보를 반환한다.
     * 
     * @param id
     * @return
     * 조회된 User 정보를 반환한다.
     */
    public Customer selectUser(String id);

    public List<Category> selectInterestByUserId(int id);
    
    public int uploadToken(String token, int userId);
    
    public String getFcmTokenbyUserId(int userId);
    
    public Customer getAgeAndSex(int userId); 
    
    
}
