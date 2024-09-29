package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IImagesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.ILikeRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;



@Service
public class LikeService {

    @Autowired
    IImagesRepository imageRepository;
    @Autowired
    ILikeRepository likeRepository;
    @Autowired
    GetUserByAuth getUserByAuth;


    @Transactional
    public ResponseService<Boolean> likeImage( Long imageId ){
        PhotoEntity image = imageRepository.findById(imageId)
            .orElseThrow( () -> CustomException.notFoundException("Image not found"));
        UserEntity user = getUserByAuth.getUser();
        Boolean delete = image.getLikes().removeIf( l -> l.getUser()
            .getId()
            .equals(user.getId())
        );

        if( delete ){
            likeRepository.deleteByUserIdAndImageId(user.getId(), imageId);
            return new ResponseService<>(new Date(), true, 200);
        }

        LikeEntity like = new LikeEntity();
        like.setUser(user);
        like.setImage(image);

        likeRepository.save(like);

        return new ResponseService<>(new Date(), true, 201);
    }

}
