package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;



public interface JpaLikeRepository extends JpaRepository<LikeEntity, Long> {
    public int deleteByUserIdAndImageId( Long userId, Long iamgeId );

    @Query("SELECT l FROM LikeEntity l WHERE l.image.id = ?1")
    public Page<ILikeProjection> findLikesByImageId(Long imageId, Pageable pageable);
}
