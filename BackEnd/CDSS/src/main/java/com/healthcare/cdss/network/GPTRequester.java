package com.healthcare.cdss.network;

import com.healthcare.cdss.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GPTRequester {

    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;
    public GPTRequester(ExceptionExtractor exceptionExtractor){
        webClient = WebClient.create();
        this.exceptionExtractor = exceptionExtractor;
    }

    public String send(String message){
        String result = attemptSend(message)
                .block();
        if(result == null || result.isEmpty())
            return null;
        return result;
    }

    private Mono<String> attemptSend(String request) {
        return webClient.post()
                .uri(AppConstants.CHAT_GPT_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), String.class)
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

