package com.ssafy.cafe.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Value;
import com.ssafy.cafe.model.dto.BookRecommendRequest;
import com.ssafy.cafe.model.dto.GPTResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class GPTService {
    private final RestTemplate restTemplate;
    private final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    private String apiKey;

    public GPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBookRecommendations(BookRecommendRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String prompt = String.format(
            "다음 관심사에 따라 5권의 책을 추천해주세요:\n\n- 관심사: %s\n- 나이: %d세\n- 성별: %s\n\n출력은 반드시 다음 형식을 따라야 합니다:\n\ntitle:[책 제목]\nauthor:[작가명]\nreason:[추천 사유]\n\n(책마다 이 형식을 반복).",
            String.join(", ", request.getInterests()),
            request.getAge(),
            request.getGender()
        );

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("messages", Arrays.asList(
            Map.of("role", "system", "content", "You are a helpful assistant that recommends books. Always respond in Korean and follow this fixed format:\n\ntitle:[책 제목]\nauthor:[작가명]\nisbn:[ISBN-13]\nreason:[추천 사유]\n\nContinue the format for each book recommendation."),
            Map.of("role", "user", "content", prompt)
        ));
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GPTResponse> response = restTemplate.postForEntity(GPT_API_URL, entity, GPTResponse.class);
        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
