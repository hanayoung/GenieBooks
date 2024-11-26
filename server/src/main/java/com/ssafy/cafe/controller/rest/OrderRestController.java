package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.OrderInfo;
import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.CustomerServiceImpl;
import com.ssafy.cafe.model.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/order")
@CrossOrigin("*")
public class OrderRestController {

	private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

	@Autowired
	private OrderService oService;

	@Autowired
	private CustomerService cService;

	@PostMapping
	@Operation(summary = "order 객체를 저장하고 추가된 Order의 id를 반환한다.")
	public Integer makeOrder(@RequestBody Order order) {
		logger.debug("makeOrder : {}", order);
		if (order.getDetails().isEmpty()) {
			return -1;
		} else {
			oService.makeOrder(order);
			return order.getId();
		}
	}

	@GetMapping("/test/{orderId}")
	@Operation(summary = "{orderId}에 해당하는 주문의 내역을 목록 형태로 반환한다." + "이 정보는 사용자 정보 화면의 주문 내역 조회에서 사용된다.")
	public OrderInfo getOrderDetailOriginal(@PathVariable Integer orderId) {
		return oService.getOrderInfo(orderId);
	}

	@GetMapping
	@Operation(summary = "사용자의 모든 주문 내역을 간략한 목록으로 반환한다.")
	public List<Order> getAllOrders(HttpServletRequest request, String id) {
		String idInCookie = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				try {
					if ("loginId".equals(cookie.getName())) {
						idInCookie = URLDecoder.decode(cookie.getValue(), "utf-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		Customer selected = cService.selectUser(id);

		if (!id.equals(idInCookie)) {
			logger.info("different cookie value : inputValue : {}, inCookie:{}", id, idInCookie);
			selected = null; // 사용자 정보 삭제.
		}
		if (selected == null) {
			return new ArrayList<>(); // 주문 내역 정보 조회 실패
		} else {
			List<Order> orders = oService.getOrderInfoByUser(selected.getCId());
			logger.debug("orders in controller : {}", orders);
			return orders;
		}
	}

	@GetMapping("/{orderId}")
	@Operation(summary = "{orderId}에 해당하는 주문의 내역을 목록 형태로 반환한다." + "이 정보는 사용자 정보 화면의 주문 내역 조회에서 사용된다.")
	public OrderInfo getOrderDetail(@PathVariable int orderId) {
		return oService.getOrderInfo(orderId);
	}

}
