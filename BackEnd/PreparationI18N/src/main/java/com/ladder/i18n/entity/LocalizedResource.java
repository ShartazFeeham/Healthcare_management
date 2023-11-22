package com.ladder.i18n.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LocalizedResource {
    @Id
    private String resourceId;

    private String defaultText;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @OneToMany(mappedBy = "parentResource", cascade = CascadeType.ALL)
    private List<Translation> translations;
}
