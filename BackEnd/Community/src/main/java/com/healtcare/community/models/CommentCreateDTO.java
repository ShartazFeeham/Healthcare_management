package com.healtcare.community.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class CommentCreateDTO {
    private String content;
    private Long parentCommentId;
    private Long parentPostId;
}
