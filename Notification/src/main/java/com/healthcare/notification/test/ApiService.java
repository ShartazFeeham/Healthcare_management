package com.healthcare.notification.test;

import com.healthcare.notification.utilities.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public ApiService(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.webClient = webClientBuilder.build();
    }

//    public String getProduct() {
//        return webClient.get()
//                .uri(AppConstants.LANGS)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block(); // Blocking for simplicity; consider using reactive programming for non-blocking
//    }

    public Mono<String> getProduct() {
        TranslationRequest request = new TranslationRequest("en", "bn", "Hello");
        Mono<String> result = webClient.post()
                .uri("http://localhost:5100/v1/translation/translate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), TranslationRequest.class)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(result);
        return result;
    }



//    public String getProduct() {
//        TranslationRequest request = new TranslationRequest("en", "bn", "Hello");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String url = "http://localhost:5100/v1/translation/translate";
//
//        String result = restTemplate.postForObject(url, request, String.class, headers);
//
//        System.out.println(result);
//
//        return result;
//    }
}
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
class TranslationRequest {
    private String from;
    private String to;
    private String text;
}
