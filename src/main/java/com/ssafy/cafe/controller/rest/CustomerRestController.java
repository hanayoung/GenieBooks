package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.User;
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
    
    @PostMapping("/signup")
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. "
    	, description = "<pre> json중에서 id, name, pass만 입력해도 정상동작한다. \n"
    			+ "아래는 aa12 사용자를 추가하는 샘플코드\n "
    			+ "{\r\n"
    			+ "  \"id\": \"aa12\",\r\n"
    			+ "  \"name\": \"aa12\",\r\n"
    			+ "  \"pass\": \"aa12\",\r\n"
    			+ "}"
    			+ "</pre>")
    public Boolean insert(@RequestBody Customer customer) {
    	logger.debug("customer.insert", customer);
    	int result = 0;
    	try {
    		result = cService.join(customer);
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
    
    public Customer login(@RequestBody Customer customer, HttpServletResponse response) throws UnsupportedEncodingException {
    	Customer selected = cService.login(customer.getId(), customer.getPwd());
        if (selected != null) {
          Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
//            Cookie cookie = new Cookie("loginId", selected.getId());
            cookie.setMaxAge(60*60*24*30); //30일
            response.addCookie(cookie);
        }
        return selected;
    }
    
    @GetMapping("/isUsed")
    @Operation(summary = "request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsedId(String id) {
        return cService.isUsedId(id);
    }

}
