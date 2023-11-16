package com.healtcare.community.services.implementations;

import com.healtcare.community.entities.Interact;
import com.healtcare.community.entities.Post;
import com.healtcare.community.exception.ItemNotFoundException;
import com.healtcare.community.models.Reaction;
import com.healtcare.community.repositories.PostRepository;
import com.healtcare.community.services.interfaces.ReactionService;
import com.healthcare.notification.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final PostRepository postRepository;
    @Override
    public String react(Reaction reaction) {
        Optional<Post> postOp = postRepository.findById(reaction.getPostId());
        if(postOp.isEmpty()) throw new ItemNotFoundException("post", reaction.getPostId().toString());
        Post post = postOp.get();
        Interact interact = new Interact();
        interact.setType(reaction.getType());
        interact.setUserId(IDExtractor.getUserID());
        for(Interact existing: post.getReactions()){
            if(existing.getUserId().equals(IDExtractor.getUserID())){
                if(interact.getType() == 0){
                    post.getReactions().remove(existing);
                    postRepository.save(post);
                    return "Reaction removed.";
                }
                existing.setType(reaction.getType());
                postRepository.save(post);
                return "Reaction updated.";
            }
        }
        post.getReactions().add(interact);
        postRepository.save(post);
        return "Reaction added.";
    }
}
