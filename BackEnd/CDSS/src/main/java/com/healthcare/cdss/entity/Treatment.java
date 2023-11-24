package com.healthcare.cdss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, message = "Condition must have a minimum length of 5 characters")
    @Column(name = "condition_name")
    private String condition;

    @NotNull(message = "Issue date cannot be null")
    @PastOrPresent(message = "Issue date must be in the past or present")
    private LocalDate issueDate;

    @NotNull(message = "Closing date cannot be null")
    @PastOrPresent(message = "Closing date must be in the past or present")
    private LocalDate closingDate;

    @Size(min = 5, message = "Medicines must have a minimum length of 5 characters")
    private String medicines;

    private String diagnoses;

    @Size(min = 5, message = "Progression must have a minimum length of 5 characters")
    private String progression;

    @Size(min = 5, message = "Doctor comment must have a minimum length of 5 characters")
    private String doctorComment;

    private String patientId;
    private String authorId;

    @JsonIgnore
    private String keywords;

    public void generateKeywords() {
        String combinedText = condition + " " + medicines + " " + diagnoses;
        String normalizedText = normalizeString(combinedText);
        Set<String> keywordSet = tokenizeString(normalizedText);
        keywords = String.join(" ", keywordSet);
    }

    @JsonIgnore
    public Set<String> getKeywordsSet(){
        return new HashSet<>(Arrays.asList(this.keywords.split(" ")));
    }

    private String normalizeString(String input) {
        String lowercase = input.toLowerCase();
        String normalized = Normalizer.normalize(lowercase, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[^\\p{ASCII}]", "");
        normalized = normalized.replaceAll("[^a-zA-Z\\s]", "");
        normalized = normalized.trim();
        return normalized;
    }

    private Set<String> tokenizeString(String input) {
        String[] words = input.split("\\s+");
        Set<String> wordSet = new HashSet<>();
        Collections.addAll(wordSet, words);
        return wordSet;
    }

    public int getSimilarityValue(Treatment otherTreatment) {
        int commonKeywords = 0;
        for (String keyword : getKeywordsSet()) {
            if (otherTreatment.getKeywords().contains(keyword)) {
                commonKeywords++;
            }
        }
        // Normalize the similarity value based on the total number of keywords, return the percentage from the ratio
        int totalKeywords = getKeywordsSet().size() + otherTreatment.getKeywordsSet().size();
        double similarityRatio = (2.0 * commonKeywords) / totalKeywords;
        return (int) (similarityRatio * 100);
    }
}
