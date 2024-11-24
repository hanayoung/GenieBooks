package com.ssafy.cafe.controller.rest;

import com.ssafy.cafe.model.dto.Attendance;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.service.AttendanceService;
import com.ssafy.cafe.model.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/attendance")
@CrossOrigin("*")
public class AttendanceRestController {
    private static final Logger logger = LoggerFactory.getLogger(AttendanceRestController.class);

    @Autowired
    CustomerService cService;

    @Autowired
    AttendanceService aService;

    @GetMapping
    @Operation(summary = "사용자의 출석 정보를 반환한다",
            description = "사용자의 로그인 시 사용된 id를 보내야함")
    public List<Date> getCurrentMonthAttendance(String id) {
        Customer selected = cService.selectUser(id);
        if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("customer", new Customer());
            return new ArrayList<>();
        } else {
            List<Attendance> attendances = aService.selectAttendance(selected.getCId());
            logger.debug("orders in controller : {}",attendances);
            List<Date> dates = attendances.stream().map(Attendance::getAttendDate)
                    .collect(Collectors.toList());
            return dates;
        }
    }

    @GetMapping("/add")
    @Operation(summary = "사용자의 출석 정보를 등록한다")
    public boolean addAttendance(String id) {
        Customer selected = cService.selectUser(id);
        if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("customer", new Customer());
            return false;
        } else {
            if(aService.checkDate(selected.getCId()) == false){
                Boolean attendance = aService.addAttendance(selected.getCId());
                logger.debug("attendance in controller : {}",attendance);
                return attendance;
            }else{
                return false; // 이미 출석체크가 되어있는 경우
            }
        }
    }
}
