package com.healtcare.appointments.controllers;

import com.healtcare.appointments.models.CommentCreateDTO;
import com.healtcare.appointments.services.interfaces.CommentService;
import com.healthcare.notification.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.create(commentCreateDTO);
        return ResponseEntity.ok("Comment created successfully");
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,
                                                @RequestParam String content) {
        commentService.update(commentId, IDExtractor.getUserID(), content);
        return ResponseEntity.ok("Comment updated successfully");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.delete(IDExtractor.getUserID(), commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
