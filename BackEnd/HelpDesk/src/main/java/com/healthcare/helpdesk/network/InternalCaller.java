package com.healthcare.helpdesk.network;

import com.healthcare.helpdesk.utilities.constants.AppConstants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class InternalCaller {

    private final WebClient webClient;
    private final ExceptionExtractor exceptionExtractor;
    private final String endPoint;

    public InternalCaller(String endPoint){
        webClient = WebClient.create();
        exceptionExtractor = new ExceptionExtractor();
        this.endPoint = endPoint;
    }

    public Mono<List<Map<String, Object>>> attemptInternalCall(String keyword) {
        try{
            return webClient.get()
                    .uri(endPoint + "/" + keyword)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                                return Mono.empty();
                            })
                    )
                    .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                    .onErrorResume(throwable -> {
                        return Mono.empty();
                    });
        }
        catch (Throwable e){
            return null;
        }
    }
}
