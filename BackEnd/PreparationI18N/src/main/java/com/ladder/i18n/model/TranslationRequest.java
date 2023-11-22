package com.ladder.i18n.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TranslationRequest{
        private String from;
        private String to;
        private String text;
}