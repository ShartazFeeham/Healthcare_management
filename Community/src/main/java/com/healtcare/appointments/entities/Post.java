package com.healtcare.appointments.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @RequiredArgsConstructor @Entity  @Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String userId;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String photoURL;
    private LocalDateTime timeCreated;
    private String type;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Interact> reactions;

    public List<Integer> getSortedReactionsByFrequency() {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each interaction type
        for (Interact interact : reactions) {
            frequencyMap.put(interact.getType(), frequencyMap.getOrDefault(interact.getType(), 0) + 1);
        }

        // Create a list of entries from the map
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        // Sort the list based on the frequency in descending order
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        // Create the result list with the sorted interaction types
        return entryList.stream().map(Map.Entry::getKey).toList();
    }
}