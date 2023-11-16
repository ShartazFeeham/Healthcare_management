package com.healtcare.community.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class PostReadDTO {
    private Long postId;
    private String authorId;
    private String title;
    private String content;
    private String date;
    private String photo;
    private int commentsCount;
    private int reactionsCount;
    private int[] reactions;
}
