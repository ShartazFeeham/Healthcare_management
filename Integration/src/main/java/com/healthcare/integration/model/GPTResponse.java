package com.healthcare.integration.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponse {

    private List<Choice> choices;

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Choice {

        private int index;
        private GPTMessage message;

    }

}
