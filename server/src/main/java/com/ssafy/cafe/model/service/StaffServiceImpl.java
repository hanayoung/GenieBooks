package com.ssafy.cafe.model.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.cafe.model.dao.CustomerDao;
import com.ssafy.cafe.model.dao.OrderDao;
import com.ssafy.cafe.model.dao.StaffDao;
import com.ssafy.cafe.model.dao.UserDao;
import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.dto.GoogleBookResponse;
import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.Staff;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.util.Constants;

/**
 * @since 2021. 6. 23.
 */
@Service
public class StaffServiceImpl implements StaffService {
	
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffDao staffDao;

	@Autowired
	private OrderDao oDao;

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

	@Override
	public List<Order> selectAllOrders() {
		List<Order> noDetailOrders = staffDao.selectAllOrders();
		List<Order> orderInfos = new ArrayList<>();
		for (Order order : noDetailOrders) {
			Order detailOrder = oDao.selectOrderDetails(order.getId());
			logger.debug("test : {}", detailOrder);
			GoogleBook book = selectByIsbn(detailOrder.getDetails().get(0).getIsbn());
			if (book.getVolumeInfo() != null && book.getVolumeInfo().getImageLinks() != null) {
				detailOrder.setRepImgUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
				detailOrder.setRepBookTitle(book.getVolumeInfo().getTitle());
			}
            logger.debug("detail Order : {}",detailOrder);
			orderInfos.add(detailOrder);
		}
		return orderInfos;
	}

	@Override
	public boolean updateOrderStatePickup(int orderId) {
		return staffDao.updateOrderStatePickup(orderId);
	}

	@Override
	public boolean updateOrderStateDone(int orderId) {
		return staffDao.updateOrderStateDone(orderId);
	}

	public GoogleBook selectByIsbn(Long isbn) {
	 	   RestTemplate restTemplate = new RestTemplate();
	 	   GoogleBook book = new GoogleBook();
	        try{
	                URI uri = UriComponentsBuilder
	                        .fromUriString(Constants.GOOGLE_BOOK_API_URL)
	                        .queryParam("q","isbn:"+isbn)
	                        .queryParam("maxResults", 1)
	                        .encode()
	                        .build()
	                        .toUri();
	                ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);

	                if(response == null || response.getBody() == null || response.getBody().getItems() == null) {
	                    uri = UriComponentsBuilder
	                            .fromUriString(Constants.GOOGLE_BOOK_API_URL)
	                            .queryParam("q",String.format("ISBN:\"%d\"", isbn))
	                            .queryParam("maxResults", 1)
	                            .encode()
	                            .build()
	                            .toUri();
	                    response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
	                }

	                book = response.getBody().getItems().get(0);
	                logger.debug("isbn : {}",book);
	        }catch (Exception e) {
	                logger.debug("exception occur : {}",e.getMessage());
	        }

	        return book;
	    }

}
