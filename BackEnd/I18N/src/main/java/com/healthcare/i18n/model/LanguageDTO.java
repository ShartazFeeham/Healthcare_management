package com.healthcare.i18n.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LanguageDTO {
    private String languageCode;
    private String languageName;
}
