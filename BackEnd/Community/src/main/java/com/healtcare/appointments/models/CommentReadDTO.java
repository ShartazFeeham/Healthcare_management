package com.healtcare.appointments.models;

import com.healtcare.appointments.entities.Comment;
import com.healtcare.appointments.utilities.TimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CommentReadDTO {
    private Long id;
    private String userId;
    private String content;
    private String timeCreated;
    private Long parentCommentId;
    private Long parentPostId;
    private List<CommentReadDTO> replies;

    private final TimeFormatter timeFormatter = new TimeFormatter();

    public CommentReadDTO(Comment comment){
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.timeCreated = timeFormatter.format(comment.getTimeCreated());
        if(comment.getParentComment() != null) this.parentCommentId = comment.getParentComment().getId();
        this.parentPostId = comment.getParentPost().getPostId();
        this.replies = comment.getReplies().stream().sorted(Comparator.comparing(Comment::getTimeCreated)).map(CommentReadDTO::new).collect(Collectors.toList());
    }
}
