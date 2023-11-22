package com.ladder.i18n.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long translationId;

    private String languageName;
    private String languageCode;

    // For MySQL
    @Column(columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String localizedText;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    @JsonIgnore
    private LocalizedResource parentResource;
}

