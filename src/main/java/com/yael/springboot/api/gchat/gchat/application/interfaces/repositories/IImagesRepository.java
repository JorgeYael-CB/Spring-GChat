package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;




public interface IImagesRepository {

    public PhotoEntity save(PhotoEntity img);
    public Optional<PhotoEntity> findById( Long id );

}
