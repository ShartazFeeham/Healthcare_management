package com.healthcare.integration.service;

import com.ladder.IntegrationService.model.GPTRequest;
import com.ladder.IntegrationService.model.GPTResponse;
import com.ladder.IntegrationService.utility.constants.OpenAIConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service @RequiredArgsConstructor
public class ChatGPTService {
    private final RestTemplate template;
    public String chat(String prompt){
        GPTRequest request=new GPTRequest(OpenAIConstants.GPT_MODEL, prompt);
        GPTResponse chatGptResponse = template.postForObject(OpenAIConstants.API_URL, request, GPTResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
