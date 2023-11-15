package com.healthapp.communityservice.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter @Setter @RequiredArgsConstructor @Entity  @Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String userId;

    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime timeCreated;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Interact> reactions;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Interact> followers;
}