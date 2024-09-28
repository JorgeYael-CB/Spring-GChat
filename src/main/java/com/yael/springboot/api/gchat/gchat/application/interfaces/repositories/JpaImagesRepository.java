package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;




public interface JpaImagesRepository extends JpaRepository<PhotoEntity, Long> {}
