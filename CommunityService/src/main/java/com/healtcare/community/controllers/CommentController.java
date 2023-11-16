package com.healtcare.community.controllers;

import com.healtcare.community.models.CommentCreateDTO;
import com.healtcare.community.services.interfaces.CommentService;
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
        commentService.update(commentId, "PSF2", content);
        return ResponseEntity.ok("Comment updated successfully");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.delete("PSF2", commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
