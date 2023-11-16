package com.healtcare.community.entities;

import java.time.LocalDateTime;
import java.util.List;
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
}