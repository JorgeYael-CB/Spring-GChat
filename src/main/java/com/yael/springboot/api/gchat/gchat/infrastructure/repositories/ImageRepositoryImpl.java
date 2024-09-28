package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IImagesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaImagesRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;


@Repository
public class ImageRepositoryImpl implements IImagesRepository {

    @Autowired
    JpaImagesRepository jpaRepo;


    @Override
    public PhotoEntity save(PhotoEntity img) {
        return jpaRepo.save(img);
    }


    @Override
    public Optional<PhotoEntity> findById(Long id) {
        return jpaRepo.findById(id);
    }

}
