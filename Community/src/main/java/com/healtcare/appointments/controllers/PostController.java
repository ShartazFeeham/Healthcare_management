package com.healtcare.appointments.controllers;

import com.healtcare.appointments.models.PostCreateDTO;
import com.healtcare.appointments.models.PostDetailDTO;
import com.healtcare.appointments.models.PostReadDTO;
import com.healtcare.appointments.models.PostUpdateDTO;
import com.healtcare.appointments.services.interfaces.PostService;
import com.healthcare.notification.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        postService.create(IDExtractor.getUserID(), postCreateDTO);
        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<List<PostReadDTO>> getPostsByType(@PathVariable String type,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<PostReadDTO> posts = postService.getByType(type, page, size);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailDTO> getById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostUpdateDTO postUpdateDTO) {
        postService.update(IDExtractor.getUserID(), postId, postUpdateDTO);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.delete(IDExtractor.getUserID(), postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}