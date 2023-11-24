package com.healthcare.cdss.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.cdss.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionExtractor {
    public CustomException extract(String errorBody){
        return new CustomException(extractErrorName(errorBody), extractErrorMessage(errorBody), extractErrorStatus(errorBody));
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
