package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;


public interface IActivityRepository {

    public Optional<ActivityEntity> findByActivity(String activity);
    public Optional<ActivityEntity> findById(Long id);

}
