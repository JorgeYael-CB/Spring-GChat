package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.ImageRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.LikeRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;



@Service
public class LikeService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    GetUserByAuth getUserByAuth;


    public ResponseService<Boolean> addLikeImage( Long imageId ){
        PhotoEntity image = imageRepository.findById(imageId)
            .orElseThrow( () -> CustomException.notFoundException("Image not found"));
        UserEntity user = getUserByAuth.getUser();
        LikeEntity like = new LikeEntity();
        like.setUser(user);
        like.setImage(image);

        likeRepository.save(like);

        return new ResponseService<>(new Date(), true, 201);
    }

}
