package com.healthcare.cdss.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.cdss.exceptions.CustomException;
import com.healthcare.cdss.utilities.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
                            return Mono.error(new CustomException(
                                    extractErrorName(errorBody),
                                    extractErrorMessage(errorBody),
                                    extractErrorStatus(errorBody)));
                        })
                )
                .bodyToMono(String.class);
    }

    private String extractErrorMessage(String errorBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(errorBody);

            if (jsonNode.has("message")) {
                return jsonNode.get("message").asText();
            } else {
                return "Error message not found in the response";
            }
        } catch (Exception e) {
            return "Error parsing the error response: " + e.getMessage();
        }
    }

    private String extractErrorName(String errorBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(errorBody);

            if (jsonNode.has("exception")) {
                return jsonNode.get("exception").asText();
            } else {
                return "Error message not found in the response";
            }
        } catch (Exception e) {
            return "Error parsing the error response: " + e.getMessage();
        }
    }
    private HttpStatus extractErrorStatus(String errorBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(errorBody);

            if (jsonNode.has("status")) {
                String status = jsonNode.get("status").asText();
                return HttpStatus.valueOf(status.substring(status.indexOf(" ") + 1));
            } else {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}

