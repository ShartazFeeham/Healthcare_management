package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Interact;
import com.healtcare.appointments.entities.Post;
import com.healtcare.appointments.exception.ItemNotFoundException;
import com.healtcare.appointments.models.Reaction;
import com.healtcare.appointments.network.NotificationRequest;
import com.healtcare.appointments.network.NotificationSender;
import com.healtcare.appointments.repositories.PostRepository;
import com.healtcare.appointments.services.interfaces.ReactionService;
import com.healthcare.notification.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final PostRepository postRepository;
    private final NotificationSender notificationSender;

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
        // if(!IDExtractor.getUserID().equals(post.getUserId())) -> commented out self interaction validation for project evaluation
        notifyPostAuthor(post, post.getUserId());
        return "Reaction added.";
    }

    private void notifyPostAuthor(Post post, String postAuthor){
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .title("Someone reacted on your post")
                .text("Your post '" + post.getContent()
                        .substring(0, Math.min(post.getContent().length(), 8))
                        + "...' has a new reaction.")
                .type("COMMUNITY")
                .suffix("Visit the post to see the more")
                .userId(postAuthor)
                .url("http://localhost:3000/health/community/post/" + post.getPostId())
                .build();
        if(post.getPhotoURL() != null && !post.getPhotoURL().isEmpty()){
            notificationRequest.setPhotoUrl(post.getPhotoURL());
        }

        notificationSender.send(notificationRequest);
    }
}
