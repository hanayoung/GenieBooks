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
	public List<GoogleBook> selectBooksbyCategory(String[] categoryList) {
		
		RestTemplate restTemplate = new RestTemplate();
		List<GoogleBook> bookList = new ArrayList<>();
		logger.debug("category List : {}",categoryList.length);
		try {
			for(String category : categoryList) {
		        URI uri = UriComponentsBuilder               
		                .fromUriString(Constants.GOOGLE_BOOK_API_URL)
		                .queryParam("q","subject:"+category)
		                .encode()
		                .build()
		                .toUri();
		        
		        ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
		        bookList.addAll(response.getBody().getItems());
			}
			return bookList;
		}catch(Exception e) {
        	logger.debug("exception occur : {}",e.getMessage());
        	return bookList;
        }

        
	}

    @Override
    public List<GoogleBook> selectRecommendBooks() {
        RestTemplate restTemplate = new RestTemplate();
        List<Long> isbnList = selectRecommendIsbn();
        List<GoogleBook> recommendBookList = new ArrayList<>();
        try{
            for (Long isbn : isbnList) {
                URI uri = UriComponentsBuilder
                        .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                        .queryParam("q","isbn:"+isbn)
                        .queryParam("maxResults", 40)
                        .encode()
                        .build()
                        .toUri();
                ResponseEntity<GoogleBookResponse> response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                
                if(response == null || response.getBody().getItems() == null) {
                    uri = UriComponentsBuilder
                            .fromUriString(Constants.GOOGLE_BOOK_API_URL)
                            .queryParam("q",String.format("ISBN:\"%d\"", isbn))
                            .encode()
                            .build()
                            .toUri();

                    response = restTemplate.getForEntity(uri, GoogleBookResponse.class);
                }
                recommendBookList.addAll(response.getBody().getItems());
            }
        }catch (Exception e) {
                logger.debug("exception occur : {}",e.getMessage());
        }

        return recommendBookList;
    }

    @Override
    public List<Long> selectRecommendIsbn() {
        return googleBookDao.selectAllIsbn();
    }

}

