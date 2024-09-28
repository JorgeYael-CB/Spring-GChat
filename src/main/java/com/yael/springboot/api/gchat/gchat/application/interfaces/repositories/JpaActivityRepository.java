package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;



public interface JpaActivityRepository extends JpaRepository<ActivityEntity, Long> {
    Optional<ActivityEntity> findByActivity(String activity);
}
