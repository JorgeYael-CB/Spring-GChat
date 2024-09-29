package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Collection;

import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;




public interface ILikeRepository {

    public LikeEntity save(LikeEntity like);
    public Collection<LikeEntity> findLikesByImageId( Long imageId );
    public int deleteByUserIdAndImageId( Long userId, Long iamgeId );

}
