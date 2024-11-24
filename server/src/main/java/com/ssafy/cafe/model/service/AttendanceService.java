package com.ssafy.cafe.model.service;

import com.ssafy.cafe.model.dto.Attendance;

import java.util.List;

public interface AttendanceService {
    public List<Attendance> selectAttendance(int userId);

    public boolean addAttendance(int userId);

    public boolean checkDate(int userId);
}
