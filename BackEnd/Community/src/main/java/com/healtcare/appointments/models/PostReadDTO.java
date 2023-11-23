package com.healtcare.appointments.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @RequiredArgsConstructor
public class PostReadDTO {
    private Long postId;
    private String authorId;
    private String title;
    private String content;
    private String date;
    private String photo;
    private int commentsCount;
    private List<Integer> reactions;
    private int reactionsCount;
}
