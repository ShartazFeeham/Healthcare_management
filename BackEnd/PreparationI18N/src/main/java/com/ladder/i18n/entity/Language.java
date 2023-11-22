package com.ladder.i18n.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long languageId;
    private String languageCode;
    private String languageName;
}
