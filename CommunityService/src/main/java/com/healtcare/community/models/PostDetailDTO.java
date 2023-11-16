package com.healtcare.community.models;

import com.healtcare.community.entities.Comment;
import com.healtcare.community.entities.Interact;
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
    private List<Comment> comments;
    private List<Interact> reactions;
}
