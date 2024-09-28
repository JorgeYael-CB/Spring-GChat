package com.yael.springboot.api.gchat.gchat.application.mappers;

import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.photos.PhotoDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;



@Component
public class PhotoMapper {

    PhotoDto photoEntityToPhotoDto( PhotoEntity photo ){
        PhotoDto img = new PhotoDto();

        img.setCountLikes(photo.getLikes().stream().count());
        img.setImage(photo.getImage());

        return img;
    }

}
