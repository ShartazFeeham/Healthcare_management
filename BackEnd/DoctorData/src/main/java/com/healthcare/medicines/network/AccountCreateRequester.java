package com.healthcare.medicines.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.medicines.exceptions.CustomException;
import com.healthcare.medicines.utilities.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AccountCreateRequester {

    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;
    public AccountCreateRequester(ExceptionExtractor exceptionExtractor){
        webClient = WebClient.create();
        this.exceptionExtractor = exceptionExtractor;
    }

    public String send(AccountCreateDTO request){
        String result = attemptSend(request)
                .block();
        if(result == null || result.isEmpty())
            return null;
        return result;
    }

    private Mono<String> attemptSend(AccountCreateDTO request) {
        return webClient.post()
                .uri(AppConstants.ACCOUNT_CREATE_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                .body(Mono.just(request), AccountCreateDTO.class)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            return Mono.error(exceptionExtractor.extract(errorBody));
                        })
                )
                .bodyToMono(String.class);
    }
}

