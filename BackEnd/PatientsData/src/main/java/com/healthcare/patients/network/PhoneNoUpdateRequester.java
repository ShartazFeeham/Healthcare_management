package com.healthcare.patients.network;

import com.healthcare.patients.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PhoneNoUpdateRequester {
    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;
    public PhoneNoUpdateRequester(ExceptionExtractor exceptionExtractor){
        webClient = WebClient.create();
        this.exceptionExtractor = exceptionExtractor;
    }

    public void send(String userId, String phoneNo){
        PhoneNoRequest request = new PhoneNoRequest(userId, phoneNo);
        Mono<String> asyncRequest = attemptSend(request);
        asyncRequest.subscribe(result -> {
            System.out.println("Call succeeded.");
        });
    }

    private Mono<String> attemptSend(PhoneNoRequest request) {
        return webClient.post()
                .uri(AppConstants.PHONE_UPDATE_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                .body(Mono.just(request), PhoneNoRequest.class)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            return Mono.error(exceptionExtractor.extract(errorBody));
                        })
                )
                .bodyToMono(String.class).onErrorReturn("Error");
    }
}
