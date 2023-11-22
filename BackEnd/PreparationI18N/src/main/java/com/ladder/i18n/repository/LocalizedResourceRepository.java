package com.ladder.i18n.repository;

import com.ladder.i18n.entity.LocalizedResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizedResourceRepository extends JpaRepository<LocalizedResource, String> {
    List<LocalizedResource> findByResourceIdContaining(String keyword);
    List<LocalizedResource> findByCreatedBy(String createdBy);
}