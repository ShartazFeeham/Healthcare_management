package com.ladder.i18n.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LocalizedResourceDTO {
    private String resourceId;
    private String defaultText;
    private String createdBy;
}
