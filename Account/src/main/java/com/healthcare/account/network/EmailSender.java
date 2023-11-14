package com.healthcare.account.network;

import com.healthcare.account.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EmailSender {

    private final WebClient webClient;

    public EmailSender(){
        webClient = WebClient.create();
    }

    public void send(String to, String subject, String message){
        EmailRequestDTO email = new EmailRequestDTO(to, subject, message);
        Mono<String> asyncRequest = asyncCall(email);
        asyncRequest.subscribe(result -> {
            System.out.println("Call succeeded.");
        });
    }

    private Mono<String> asyncCall(EmailRequestDTO requestDTO){
        return attemptSend(requestDTO)
                .onErrorReturn("Error");
    }

    private Mono<String> attemptSend(EmailRequestDTO email) {
        return webClient.post()
                .uri(AppConstants.EMAIL_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(email), EmailRequestDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
