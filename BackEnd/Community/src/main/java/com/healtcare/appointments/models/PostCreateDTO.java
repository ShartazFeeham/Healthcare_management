package com.healtcare.appointments.models;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @RequiredArgsConstructor
public class PostCreateDTO {
    private String title;
    private String content;
    private String type;
    private String photo;
}