package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Attendance;

import java.util.List;

public interface AttendanceDao {
    List<Attendance> selectAttendance(int userId);

    boolean addAttendance(int userId);

    boolean checkDate(int userId);
}
