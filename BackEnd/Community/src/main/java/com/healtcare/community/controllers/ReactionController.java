package com.healtcare.community.controllers;

import com.healtcare.community.models.Reaction;
import com.healtcare.community.services.interfaces.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("posts/react")
public class ReactionController {
    private final ReactionService reactionService;
    @PostMapping
    public ResponseEntity<String> react(@RequestBody Reaction reaction){
        return ResponseEntity.ok(reactionService.react(reaction));
    }
}
