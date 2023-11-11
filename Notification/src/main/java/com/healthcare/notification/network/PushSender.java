package com.healthcare.notification.network;

import com.healthcare.notification.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PushSender {

    private final WebClient webClient;

    public PushSender(){
        webClient = WebClient.create();
    }

    public void send(String to, String title, String subTitle, String body){
        PushRequestDTO push = new PushRequestDTO(to, body, title, subTitle);
        String result = attemptSend(push)
                .onErrorReturn("Error")
                .block();
    }

    private Mono<String> attemptSend(PushRequestDTO push) {
        return webClient.post()
                .uri(AppConstants.PUSH_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(push), PushRequestDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}

