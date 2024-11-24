package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ssafy.cafe.model.dto.*;
import com.ssafy.cafe.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/customer")
@CrossOrigin("*")
public class CustomerRestController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
    
    @Autowired
    CustomerService cService;

    @Autowired
    OrderService oService;
    
    @PostMapping("/signup")
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. ")
    public Boolean insert(@RequestBody Customer customer) {
    	logger.debug("customer.insert : {}", customer);
    	int result = 0;
    	try {
    		result = cService.join(customer);
    		logger.debug("result : {}",result);
    	}catch(Exception e) {
            logger.debug("error : {}",e.getMessage());
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
    
    public Customer login(@RequestBody Customer customer, HttpServletResponse response) throws UnsupportedEncodingException {
    	Customer selected = cService.login(customer.getId(), customer.getPwd());
        if (selected != null) {
          Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
          	cookie.setPath("/");
            cookie.setMaxAge(60*60*24*30); //30일
            response.addCookie(cookie);
            logger.debug("selected :{}",selected);
        }
        logger.debug("selected  2:{}",selected);
        return selected;
    }
    
    @GetMapping("/isUsed")
    @Operation(summary = "request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsedId(String id) {
        return cService.isUsedId(id);
    }


    // 위에 꺼 대신해서 이걸 만들었다.
    // password를 sharedpreference에 저장하면 안되니, id만 받는데,
    // 이 id와 쿠키에 있는 id가 같은지 확인해서 로그인 사용자를 조회해서 리턴함.
    @GetMapping("/info")
    @Operation(summary = "사용자의 정보와 함께 사용자의 주문 내역, 사용자 등급 정보를 반환한다.",
            description = "사용자의 로그인 시 사용된 id를 보내야함")
    public Map<String, Object> getInfo(HttpServletRequest request, String id) {
        String idInCookie = "";
        Cookie [] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                try {
                    if("loginId".equals(cookie.getName())){
                        idInCookie = URLDecoder.decode(cookie.getValue(), "utf-8");
                        System.out.println("value : "+URLDecoder.decode(cookie.getValue(), "utf-8"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        Customer selected = cService.selectUser(id);

        if(!id.equals(idInCookie)) {
            logger.info("different cookie value : inputValue : {}, inCookie:{}", id, idInCookie);
            selected = null; // 사용자 정보 삭제.
        }else {
            logger.info("valid cookie value : inputValue : {}, inCookie:{}", id, idInCookie);
        }

        if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("customer", new Customer());
            return map; // 정보 조회 실패
        } else {
            Map<String, Object> info = new HashMap<>();
            List<Category> interest = cService.selectInterestByUserId(selected.getCId());
            selected.setCategory(interest);
            info.put("customer", selected);
            List<Order> orders = oService.getOrderInfoByUser(selected.getCId());
            logger.debug("orders in controller : {}",orders);
            if(orders.isEmpty()){
                info.put("completeCnt",0);
                info.put("inCompleteCnt",0);
            }else {
                List<Order> completeOrders = orders.stream()
                        .filter(order -> order.getCompleted() == true)
                        .collect(Collectors.toList());

                info.put("completeCnt", completeOrders.size());
                info.put("inCompleteCnt", orders.size() - completeOrders.size());
            }

            return info;
        }
    }

}
