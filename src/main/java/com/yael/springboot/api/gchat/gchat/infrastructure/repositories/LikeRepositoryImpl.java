package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.ILikeRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaLikeRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;



@Repository
public class LikeRepositoryImpl implements ILikeRepository {

    @Autowired
    JpaLikeRepository jpaLikeRepository;

    @Override
    public LikeEntity save(LikeEntity like) {
        return jpaLikeRepository.save(like);
    }

    @Override
    public int deleteByUserIdAndImageId(Long userId, Long iamgeId) {
        return jpaLikeRepository.deleteByUserIdAndImageId(userId, iamgeId);
    }

    @Override
    public Page<ILikeProjection> findLikesByImageId(Long imageId, Pageable pageable) {
        return jpaLikeRepository.findLikesByImageId(imageId, pageable);
    }

}
