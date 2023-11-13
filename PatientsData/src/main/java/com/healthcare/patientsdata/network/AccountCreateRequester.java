package com.healthcare.patientsdata.network;

import com.healthcare.patientsdata.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AccountCreateRequester {

    private final WebClient webClient;

    public AccountCreateRequester(){
        webClient = WebClient.create();
    }

    public String send(AccountCreateDTO request){
        return attemptSend(request)
                .onErrorReturn(null)
                .block();
    }

    private Mono<String> attemptSend(AccountCreateDTO request) {
        return webClient.post()
                .uri(AppConstants.ACCOUNT_CREATE_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                .body(Mono.just(request), AccountCreateDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}

