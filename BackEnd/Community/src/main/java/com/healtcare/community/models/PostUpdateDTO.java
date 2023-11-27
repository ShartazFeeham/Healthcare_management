package com.healtcare.community.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @RequiredArgsConstructor
public class PostUpdateDTO {
    private String title;
    private String content;
    private MultipartFile photo;
}