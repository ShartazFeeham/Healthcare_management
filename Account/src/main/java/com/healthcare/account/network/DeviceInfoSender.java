package com.healthcare.account.network;

import com.healthcare.account.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DeviceInfoSender {

    private final WebClient webClient;

    public DeviceInfoSender(){
        webClient = WebClient.create();
    }

    public void send(String userId, String email, String deviceCode){
        DeviceRequest request = new DeviceRequest(userId, email, deviceCode);
        String result = attemptSend(request)
                .onErrorReturn("Error")
                .block();
    }

    private Mono<String> attemptSend(DeviceRequest request) {
        return webClient.post()
                .uri(AppConstants.DEVICE_INFO_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AppConstants.INTERNAL_TOKEN)
                .body(Mono.just(request), DeviceRequest.class)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            return Mono.error(new RuntimeException("Error during device info send"));
                        })
                )
                .bodyToMono(String.class);
    }
}
