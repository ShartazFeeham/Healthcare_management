package com.healtcare.community.services.implementations;

import com.healtcare.community.entities.Comment;
import com.healtcare.community.entities.Post;
import com.healtcare.community.enums.PostType;
import com.healtcare.community.exception.AccessDeniedException;
import com.healtcare.community.exception.ItemNotFoundException;
import com.healtcare.community.models.*;
import com.healtcare.community.repositories.PostRepository;
import com.healtcare.community.services.interfaces.PostService;
import com.healtcare.community.utilities.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TimeFormatter timeFormatter;

    @Override
    public void create(String userId, PostCreateDTO postCreateDTO) {
        Post post = new Post();
        checkPermissionForPost(postCreateDTO.getType(), userId);
        post.setTitle(postCreateDTO.getTitle());
        post.setContent(postCreateDTO.getContent());
        post.setUserId(userId);
        post.setType(postCreateDTO.getType());
        post.setPhotoURL(postCreateDTO.getPhoto());
        post.setTimeCreated(LocalDateTime.now());

        postRepository.save(post);
    }

    private void checkPermissionForPost(String type, String userId) {
        char userType = userId.charAt(0);
        switch (userType) {
            case 'P' -> {
                if (!(type.equals(PostType.STATUS.getValue()) || type.equals(PostType.FEEDBACK.getValue()))) {
                    throw new AccessDeniedException(type + " post. You (Patient) can only post Status and Feedback.");
                }
            }
            case 'D' -> {
                if (!(type.equals(PostType.STATUS.getValue()) || type.equals(PostType.ARTICLE.getValue())
                        || type.equals(PostType.FIRST_AID.getValue()))) {
                    throw new AccessDeniedException(type + " post. You (Doctor) can only post Status, Article, and First Aid.");
                }
            }
            case 'A' -> {
                if (type.equals(PostType.FEEDBACK.getValue())) {
                    throw new AccessDeniedException(type + " post. Admins cannot post Feedback.");
                }
            }
            default -> throw new AccessDeniedException("Invalid user type.");
        }
    }

    @Override
    public List<PostReadDTO> getByType(String type, int page, int size, boolean sortType) {
        Page<Post> postsPage;
        if (sortType) {
            postsPage = postRepository.findByTypeOrderByTimeCreatedDesc(type, PageRequest.of(page, size));
        } else {
            postsPage = postRepository.findByTypeOrderByTimeCreatedAsc(type, PageRequest.of(page, size));
        }
        List<Post> posts = postsPage.getContent();
        return posts.stream().map(this::convertToReadDTO).toList();
    }

    private PostReadDTO convertToReadDTO(Post post) {
        PostReadDTO postReadDTO = new PostReadDTO();
        postReadDTO.setPostId(post.getPostId());
        postReadDTO.setAuthorId(post.getUserId());
        postReadDTO.setTitle(post.getTitle());
        postReadDTO.setContent(post.getContent());
        postReadDTO.setDate(timeFormatter.format(post.getTimeCreated()));
        postReadDTO.setPhoto(post.getPhotoURL());
        postReadDTO.setCommentsCount(post.getComments().size());
        postReadDTO.setReactions(post.getSortedReactionsByFrequency());
        postReadDTO.setReactionsCount(post.getReactions().size());
        return postReadDTO;
    }


    @Override
    public PostDetailDTO getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()) {
            throw new ItemNotFoundException("post", postId.toString());
        }
        return convertToDetailDTO(post.get());
    }

    private PostDetailDTO convertToDetailDTO(Post post) {
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        postDetailDTO.setPostId(post.getPostId());
        postDetailDTO.setUserId(post.getUserId());
        postDetailDTO.setTitle(post.getTitle());
        postDetailDTO.setContent(post.getContent());
        postDetailDTO.setPhotoURL(post.getPhotoURL());
        postDetailDTO.setTimeCreated(timeFormatter.format(post.getTimeCreated()));
        postDetailDTO.setType(post.getType());
        postDetailDTO.setComments(post.getComments().stream()
                .filter(c -> c.getParentComment() == null)
                .sorted(Comparator.comparing(Comment::getTimeCreated).reversed())
                .map(CommentReadDTO::new)
                .collect(Collectors.toList()));
        postDetailDTO.setReactions(post.getReactions());
        return postDetailDTO;
    }


    @Override
    public void update(String userId, Long postId, PostUpdateDTO postUpdateDTO) {
        Optional<Post> postOp = postRepository.findById(postId);
        if(postOp.isEmpty()) {
            throw new ItemNotFoundException("post", postId.toString());
        }
        Post post = postOp.get();
        verifyOwner(userId, post);
        post.setContent(postUpdateDTO.getContent());
        post.setTitle(postUpdateDTO.getTitle());
        // handle photo upload photo
        postRepository.save(post);
    }

    @Override
    public void delete(String userId, Long postId) {
        Optional<Post> postOp = postRepository.findById(postId);
        if(postOp.isEmpty()) {
            throw new ItemNotFoundException("post", postId.toString());
        }
        Post post = postOp.get();
        if(!userId.startsWith("A")) verifyOwner(userId, post);
        postRepository.delete(post);
    }

    @Override
    public List<PostReadDTO> getTopSiteFeedbacks() {
        List<Post> posts = postRepository.findByTypeOrderByTimeCreatedAsc("feedback", PageRequest.of(0, 3)).getContent();
        return posts.stream().map(this::convertToReadDTO).toList();
    }

    private void verifyOwner(String userId, Post post){
        if(!post.getUserId().equals(userId)){
            throw new AccessDeniedException("post ("+post.getPostId()+"). "+"You don't have permission to " +
                    "make any changes on this post as you don't own it.");
        }
    }
}
