package com.healthcare.notification.network;

import com.healthcare.notification.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SMSSender {

    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;

    public SMSSender(ExceptionExtractor exceptionExtractor){
        webClient = WebClient.create();
        this.exceptionExtractor = exceptionExtractor;
    }

    public void send(String receiverNo, String text){
        SMSRequestDTO request = new SMSRequestDTO(receiverNo, text);
        Mono<String> asyncRequest = attemptSend(request);
        asyncRequest.subscribe(result -> {
            System.out.println("Call succeeded.");
        });
    }

    private Mono<String> attemptSend(SMSRequestDTO request) {
        return webClient.post()
                .uri(AppConstants.SMS_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                .body(Mono.just(request), EmailRequestDTO.class)
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

