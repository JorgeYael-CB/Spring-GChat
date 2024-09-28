package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;





public interface JpaImagesRepository extends JpaRepository<PhotoEntity, Long> {
    @Query("SELECT p.image as image, SIZE(p.likes) as countLikes, p.id as id FROM PhotoEntity p WHERE p.id = ?1")
    Optional<IPhotoPreviewProjection> findByIdWithLikeCount(Long id);
}
