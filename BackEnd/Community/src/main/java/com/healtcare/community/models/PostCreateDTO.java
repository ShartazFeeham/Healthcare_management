package com.healtcare.community.models;

import lombok.*;

@Getter @Setter @RequiredArgsConstructor
public class PostCreateDTO {
    private String title;
    private String content;
    private String type;
    private String photo;
}