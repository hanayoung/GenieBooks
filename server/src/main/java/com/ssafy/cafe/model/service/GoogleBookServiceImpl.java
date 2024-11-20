package com.ssafy.cafe.model.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    
}

