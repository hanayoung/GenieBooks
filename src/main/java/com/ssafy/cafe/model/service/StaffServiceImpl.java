package com.ssafy.cafe.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.CustomerDao;
import com.ssafy.cafe.model.dao.StaffDao;
import com.ssafy.cafe.model.dao.UserDao;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Staff;
import com.ssafy.cafe.model.dto.User;

/**
 * @since 2021. 6. 23.
 */
@Service
public class StaffServiceImpl implements StaffService {
    
    @Autowired
    private StaffDao staffDao;

    @Override
    public int join(Staff staff) {
        return staffDao.insert(staff);

    }

    @Override
    public Staff login(String id, String pwd) {
    	Staff staff = staffDao.selectById(id);
        if (staff != null && staff.getPwd().equals(pwd)) {
            return staff;
        } else {
            return null;
        }
    }


    @Override
    public boolean isUsedId(String id) {
        return staffDao.selectById(id) != null;
    }
    
    @Override
    public Staff selectUser(String id) {
    	Staff staff = staffDao.selectById(id);
        if (staff != null) {
            return staff;
        } else {
            return null;
        }
    }
    
}

