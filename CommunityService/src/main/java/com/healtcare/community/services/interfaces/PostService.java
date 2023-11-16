package com.healtcare.community.services.interfaces;

import com.healtcare.community.entities.Post;
import com.healtcare.community.models.PostCreateDTO;
import com.healtcare.community.models.PostDetailDTO;
import com.healtcare.community.models.PostReadDTO;
import com.healtcare.community.models.PostUpdateDTO;

import java.util.List;

public interface PostService {
    // Post crud operations
    public void create(String userId, PostCreateDTO postCreateDTO);
    public List<PostReadDTO> getByType(String type, int page, int size);
    public PostDetailDTO getPostById(Long postId);
    public void update(String userId, Long postId, PostUpdateDTO postUpdateDTO);
    public void delete(String userId, Long postId);
}
