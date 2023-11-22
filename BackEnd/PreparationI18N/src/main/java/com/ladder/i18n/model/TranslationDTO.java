package com.ladder.i18n.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationDTO {
    private String languageName;
    private String languageCode;
    private String localizedText;
    private String resourceId;
}
