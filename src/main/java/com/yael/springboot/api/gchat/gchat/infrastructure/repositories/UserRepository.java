package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail( String email );
    Optional<UserEntity> findByEmailOrName( String email, String name );
    Optional<UserEntity> findByEmailOrId(String email, Long id);
}

