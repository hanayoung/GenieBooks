package com.ssafy.cafe.controller.rest;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.FirebaseCloudMessageService;
import com.ssafy.cafe.model.service.FirebaseCloudMessageServiceWithData;

@RestController
@RequestMapping("/rest/token")
@CrossOrigin("*")
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	
    @Autowired
    FirebaseCloudMessageService service;
    
    @Autowired
    FirebaseCloudMessageServiceWithData serviceWithData;
    
    @Autowired
    CustomerService cService;
    
    @PostMapping("/sendDataMessageTo")
    public void sendDataMessageTo(String token, String title, String body) throws IOException {
        logger.info("sendMessageTo : token:{}, title:{}, body:{}", token, title, body);
        serviceWithData.sendDataMessageTo(token, title, body);
    }
    
}
