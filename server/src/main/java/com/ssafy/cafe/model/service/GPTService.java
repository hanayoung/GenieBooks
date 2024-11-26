package com.ssafy.cafe.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.cafe.controller.rest.CustomerRestController;
import com.ssafy.cafe.model.dto.BookRecommendRequest;
import com.ssafy.cafe.model.dto.Category;
import com.ssafy.cafe.model.dto.GPTResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class GPTService {
	private final RestTemplate restTemplate;
	private final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final Logger logger = LoggerFactory.getLogger(GPTService.class);

	@Value("${openai.api.key}")
	private String apiKey;
	

	public GPTService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getBookRecommendations(BookRecommendRequest request) {
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);
		logger.debug("api key : {}",apiKey);
		
		List<Category> categoryList = request.getInterests();
		
		String categoryListString = "";
		categoryList.forEach(it -> categoryListString.concat(it.getName()+", "));
		String prompt = String.format(
				"다음 관심사에 따라 5권의 책을 추천해주세요:\n\n- 관심사: %s\n- 나이: %d세\n- 성별: %s\n\n출력은 반드시 다음 형식을 따라야 합니다:\n\ntitle:[책 제목]\nauthor:[작가명]\nreason:[추천 사유]\n\n(책마다 이 형식을 반복). 이 형식을 제외하고는 다른 말은 하지말아야 합니다.",
				categoryListString, request.getAge(), request.getSex().getName());

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("model", "gpt-4o-mini");
		requestBody.put("messages", Arrays.asList(Map.of("role", "system", "content",
				"You are a helpful assistant that recommends books. Always respond in Korean and follow this fixed format:\n\ntitle:[책 제목]\nauthor:[작가명]\nreason:[추천 사유]\n\nContinue the format for each book recommendation."),
				Map.of("role", "user", "content", prompt)));
		requestBody.put("temperature", 0.7);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<GPTResponse> response = restTemplate.postForEntity(GPT_API_URL, entity, GPTResponse.class);
		return response.getBody().getChoices().get(0).getMessage().getContent();
	}
}
