package com.healthcare.integration.config;

import com.ladder.IntegrationService.utility.constants.OpenAIConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    @Bean
    public RestTemplate template(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + OpenAIConstants.API_KEY);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
