package com.ssafy.cafe.model.service;

import com.ssafy.cafe.Custom404ErrorController;
import com.ssafy.cafe.model.dao.AttendanceDao;
import com.ssafy.cafe.model.dao.CustomerDao;
import com.ssafy.cafe.model.dto.Attendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendanceServiceImpl implements AttendanceService{
    private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Attendance> selectAttendance(int userId) {
        return attendanceDao.selectAttendance(userId);
    }

    @Override
    public boolean addAttendance(int userId) {
        Boolean result = attendanceDao.addAttendance(userId);
        int currentPoint = customerDao.getUserPoint(userId);
        if(result == true) {
            customerDao.updatePoint(userId, currentPoint+100);
        }
        return result;
    }

    @Override
    public boolean checkDate(int userId) {
        logger.debug("checkDate : {}",attendanceDao.checkDate(userId));
        return attendanceDao.checkDate(userId);
    }


}
