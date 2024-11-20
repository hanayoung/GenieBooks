package com.ssafy.cafe.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.cafe.model.dao.CustomerDao;
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Interest;

/**
 * @since 2021. 6. 23.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Autowired
    private CustomerDao customerDao;

    @Override
    public int join(Customer customer) {
    	int result = customerDao.insert(customer);
    	Customer newCustomer = customerDao.selectById(customer.getId());
    	logger.debug("customer.inserted {}", customer);
    	logger.debug("customer.new {}", newCustomer);
    	List<Category> list = customer.getCategory();
    	for (Category category : list) {
    		Interest interest = new Interest(newCustomer.getCId(),category);
        	logger.debug("customer.interest {}", interest);
    		customerDao.insertInterest(interest);
		}
        return result;

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

