package com.ssafy.cafe.controller.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.ssafy.cafe.model.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.Staff;
import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.FirebaseCloudMessageService;
import com.ssafy.cafe.model.service.FirebaseCloudMessageServiceWithData;
import com.ssafy.cafe.model.service.StaffService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/staff")
@CrossOrigin("*")
public class StaffRestController {
	
    private static final Logger logger = LoggerFactory.getLogger(StaffRestController.class);
    
    @Autowired
    StaffService sService;
    
    @Autowired
    FirebaseCloudMessageService service;
    
    @Autowired
    FirebaseCloudMessageServiceWithData serviceWithData;
    
    @Autowired
    CustomerService cService;

    @Autowired
    OrderService oService;
    
    @PostMapping("/signup")
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. ")
    public Boolean insert(@RequestBody Staff staff) {
    	int result = 0;
    	try {
            staff.setSId(null);
    		result = sService.join(staff);
    	}catch(Exception e) {
    		result = -1;
    	}
    	
        if(result == 1) {
            return true;
        }else {
            return false;
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다."
    , description = "<pre>id와 pass 두개만 넘겨도 정상동작한다. \n 아래는 id, pass만 입력한 샘플코드\n"
    		+ "{\r\n"
    		+ "  \"id\": \"aa12\",\r\n"
    		+ "  \"pass\": \"aa12\"\r\n"
    		+ "}"
    		+ "</pre>")
    
    public Staff login(@RequestBody Staff staff, HttpServletResponse response) throws UnsupportedEncodingException {
    	Staff selected = sService.login(staff.getId(), staff.getPwd());
        if (selected != null) {
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
            cookie.setMaxAge(60*60*24*30); //30일
            response.addCookie(cookie);
        }
        return selected;
    }
    
    @GetMapping("/isUsed")
    @Operation(summary = "request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsedId(String id) {
        return sService.isUsedId(id);
    }
    
    @GetMapping("/order")
    @Operation(summary = "픽업 대기 중인 목록 반환")
    public List<Order> getAllOrders() {
    	List<Order> orders = sService.selectAllOrders();
        logger.debug("staffs in controller : {}",orders);
        return orders;
    }
    
    @PutMapping("/order/done")
    @Operation(summary = "도서 준비 완료됨 상태, fcm을 사용자에게 보냄")
    public Boolean updateOrderStateDone(@RequestBody Map<String, Integer> payload) throws IOException {
    	int orderId = payload.get("orderId");
    	int userId = payload.get("userId");
    	Boolean result = sService.updateOrderStateDone(orderId);
        String fcmToken = cService.getFcmTokenbyUserId(userId);
        serviceWithData.sendDataMessageTo(fcmToken,"픽업 준비 완료", "도서가 준비되었습니다");
        return result;
    }

    @PutMapping("/order/pickup")
    @Operation(summary = "직원이 수령함")
    public Boolean updateOrderStatePickup(@RequestBody Map<String, Integer> payload) throws IOException {
        int orderId = payload.get("orderId");
        int userId = payload.get("userId");
        Boolean result = sService.updateOrderStatePickup(orderId);
        String fcmToken = cService.getFcmTokenbyUserId(userId);
        serviceWithData.sendDataMessageTo(fcmToken,"수령 완료", "수령이 완료되었습니다. 즐거운 시간 되세요🥰");
        oService.updatePickupTime(orderId);
        return result;
    }

}
