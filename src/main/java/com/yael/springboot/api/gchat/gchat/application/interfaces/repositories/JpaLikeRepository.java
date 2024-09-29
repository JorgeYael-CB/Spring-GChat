package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;



public interface JpaLikeRepository extends JpaRepository<LikeEntity, Long> {
    public Collection<LikeEntity> findLikesByImageId( Long imageId );
    public int deleteByUserIdAndImageId( Long userId, Long iamgeId );
}
