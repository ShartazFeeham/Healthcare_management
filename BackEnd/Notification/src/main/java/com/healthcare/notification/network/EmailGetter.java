package com.healthcare.notification.network;

import com.healthcare.notification.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EmailGetter {

    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;

    public EmailGetter(ExceptionExtractor exceptionExtractor){
        webClient = WebClient.create();
        this.exceptionExtractor = exceptionExtractor;
    }

    public String get(String userId){
        String result = attemptSend(userId)
                .block();
        if(result == null || result.isEmpty())
            return null;
        return result;
    }

    private Mono<String> attemptSend(String userId) {
        return webClient.get()
                .uri(AppConstants.GET_EMAIL_URL + userId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
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

