package com.healtcare.appointments.services.interfaces;
import com.healtcare.appointments.models.CommentCreateDTO;

public interface CommentService {
    // Comment CRUD operations
    void create(CommentCreateDTO commentCreateDTO);
    void update(Long commentId, String userId, String content);
    void delete(String userId, Long commentId);
}