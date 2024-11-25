package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.Attendance;
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
		int result = 0;
		Customer check = customerDao.selectById(customer.getId());
		if(check != null) result = -1;
		else {
			result = customerDao.insert(customer);
			List<Category> list = customer.getCategory();
			Integer cId = customer.getCId();
			try{
				for (Category category : list) {
					Interest interest = new Interest(cId, category);
					customerDao.insertInterest(interest);
				}
			}catch (Exception e) {
				logger.debug("error occur in impl : {}",e.getMessage());
	            delete(customer.getCId());
			}

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
	public void delete(int id) {
		customerDao.delete(id);
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

	@Override
	public List<Category> selectInterestByUserId(int id) {
		List<Category> categoryList = customerDao.selectInterestByUserId(id);
		if(categoryList.isEmpty()) {
			return null;
		}else{
			return categoryList;
		}
	}

	@Override
	public int uploadToken(String token, int userId) {
		return customerDao.uploadToken(token, userId);
	}

	@Override
	public String getFcmTokenbyUserId(int userId) {
		return customerDao.getFcmTokenbyUserId(userId);
	}

}
