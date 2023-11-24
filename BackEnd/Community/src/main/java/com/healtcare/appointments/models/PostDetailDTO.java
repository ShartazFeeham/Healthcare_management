package com.healtcare.appointments.models;

import com.healtcare.appointments.entities.Comment;
import com.healtcare.appointments.entities.Interact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostDetailDTO {
    private Long postId;
    private String userId;
    private String title;
    private String content;
    private String photoURL;
    private String timeCreated;
    private String type;
    private List<CommentReadDTO> comments;
    private List<Interact> reactions;
}
