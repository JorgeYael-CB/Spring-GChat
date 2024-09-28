package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IActivityRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaActivityRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;




public class ActivityRepositoryImpl implements IActivityRepository {

    @Autowired
    JpaActivityRepository jpaRepo;

    @Override
    public Optional<ActivityEntity> findByActivity(String activity) {
        return jpaRepo.findByActivity(activity);
    }

    @Override
    public Optional<ActivityEntity> findById(Long id) {
        return jpaRepo.findById(id);
    }


}
