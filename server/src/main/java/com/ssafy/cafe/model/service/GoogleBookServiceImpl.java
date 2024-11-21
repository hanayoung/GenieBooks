package com.ssafy.cafe.model.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.cafe.model.dao.GoogleBookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.cafe.model.dto.GoogleBook;
import com.ssafy.cafe.model.dto.GoogleBookResponse;
import com.ssafy.cafe.util.Constants;
import org.springframework.http.ResponseEntity;

/**
 * @since 2021. 6. 23.
 */
@Service
public class GoogleBookServiceImpl implements GoogleBookService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleBookServiceImpl.class);

    @Autowired
    private GoogleBookDao googleBookDao;

	@Override
	public List<GoogleBook> selectBooksbyCategory(String category) {
		
		RestTemplate restTemplate = new RestTemplate();
			
        URI uri = UriComponentsBuilder               
                .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                .queryParam("q","subject:"+category)
                .encode()
                .build()
                .toUri();
        try {
            ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
            return response.getBody().getItems();
        }catch(Exception e) {
        	logger.debug("exception occur : {}",e.getMessage());
        	return new ArrayList<>();
        }
        
	}

    @Override
    public List<GoogleBook> selectRecommendBooks() {
        RestTemplate restTemplate = new RestTemplate();
        // 우선 db에서 isbn 목록을 가져온 후, book 목록을 google books api에 던지기
        List<Long> isbnList = selectRecommendIsbn();
        List<GoogleBook> recommendBookList = new ArrayList<>();
        logger.debug("isbnList size : {}",isbnList.size());
        try{
            for (Long isbn : isbnList) {
            	logger.debug("isbn :{}",isbn);
                URI uri = UriComponentsBuilder
                        .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                        .queryParam("q","isbn:"+isbn)
                        .encode()
                        .build()
                        .toUri();
                logger.debug("uri : {}",uri);
                ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                logger.debug("response :{}",response);
                
                if(response == null || response.getBody().getItems() == null) {
                    uri = UriComponentsBuilder
                            .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                            .queryParam("q",String.format("ISBN:\"%d\"", isbn))
                            .encode()
                            .build()
                            .toUri();
                    
                    logger.debug("second uri : {}",uri);
                    response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                    logger.debug("response :{}",response.getBody().getItems());
                }
                recommendBookList.addAll(response.getBody().getItems());
            }
            return recommendBookList;
        }catch (Exception e) {
                logger.debug("exception occur : {}",e.getMessage());
                return recommendBookList;
        }
    }

    @Override
    public List<Long> selectRecommendIsbn() {
        return googleBookDao.selectAllIsbn();
    }


}

