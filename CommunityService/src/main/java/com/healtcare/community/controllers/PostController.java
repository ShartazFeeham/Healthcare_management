package com.healtcare.community.controllers;

import com.healtcare.community.entities.Post;
import com.healtcare.community.models.PostCreateDTO;
import com.healtcare.community.models.PostDetailDTO;
import com.healtcare.community.models.PostReadDTO;
import com.healtcare.community.models.PostUpdateDTO;
import com.healtcare.community.services.interfaces.PostService;
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
        postService.create("DSF31", postCreateDTO);
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
        postService.update("AMF3", postId, postUpdateDTO);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.delete("AMF3", postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
