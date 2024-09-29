package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;




public interface ILikeRepository {

    public LikeEntity save(LikeEntity like);
    public int deleteByUserIdAndImageId( Long userId, Long iamgeId );
    public Page<ILikeProjection> findLikesByImageId(Long imageId, Pageable pageable);

}
