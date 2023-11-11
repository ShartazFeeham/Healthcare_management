package com.healthcare.notification.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/getProduct")
    public String getProduct() {
        // Block and get the result as a string
        String result = apiService.getProduct()
                .onErrorReturn("Error")
                .block();

        // Process the result if needed
        return result;
    }


}
