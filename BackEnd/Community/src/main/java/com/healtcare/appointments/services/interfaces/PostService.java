package com.healtcare.appointments.services.interfaces;

import com.healtcare.appointments.models.PostCreateDTO;
import com.healtcare.appointments.models.PostDetailDTO;
import com.healtcare.appointments.models.PostReadDTO;
import com.healtcare.appointments.models.PostUpdateDTO;

import java.util.List;

public interface PostService {
    // Post crud operations
    public void create(String userId, PostCreateDTO postCreateDTO);
    public List<PostReadDTO> getByType(String type, int page, int size);
    public PostDetailDTO getPostById(Long postId);
    public void update(String userId, Long postId, PostUpdateDTO postUpdateDTO);
    public void delete(String userId, Long postId);
}
