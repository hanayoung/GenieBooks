package com.ssafy.cafe.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.CustomerDao;
import com.ssafy.cafe.model.dao.UserDao;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.User;

/**
 * @since 2021. 6. 23.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerDao customerDao;

    @Override
    public int join(Customer customer) {
        return customerDao.insert(customer);

    }

    @Override
    public Customer login(String id, String pwd) {
        Customer customer = customerDao.selectById(id);
        if (customer != null && customer.getPwd().equals(pwd)) {
            return customer;
        } else {
            return null;
        }
    }


    @Override
    public boolean isUsedId(String id) {
        return customerDao.selectById(id) != null;
    }
    
    @Override
    public Customer selectUser(String id) {
    	Customer customer = customerDao.selectById(id);
        if (customer != null) {
            return customer;
        } else {
            return null;
        }
    }
    
}

