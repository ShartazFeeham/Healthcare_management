package com.healtcare.community.repositories;

import com.healtcare.community.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTypeOrderByTimeCreatedDesc(String type, Pageable pageable);
    Page<Post> findByTypeOrderByTimeCreatedAsc(String type, PageRequest of);
}
