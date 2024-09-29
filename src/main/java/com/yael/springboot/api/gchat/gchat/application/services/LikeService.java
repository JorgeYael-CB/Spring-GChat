package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
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

    @Transactional(readOnly=true)
    public ResponseServicePagination<List<ILikeProjection>> getLikesByImageId(Long imageId, PaginationDto paginationDto){
        Pageable page = PageRequest.of(
            paginationDto.getPage(),
            paginationDto.getLimit(),
            Sort.by(Sort.Direction.DESC, "id")
        );

        Page<ILikeProjection> likes = likeRepository.findLikesByImageId(imageId, page);

        return new ResponseServicePagination<>(likes.toList(), likes.getTotalElements(), likes.getTotalPages(), 200);
    }
}
