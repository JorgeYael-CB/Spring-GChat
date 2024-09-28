package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



public interface IUserRepository {


    public Optional<UserEntity> findById(Long id);
    public Optional<UserEntity> findByNameOrEmail(String name, String email);
    public Optional<UserEntity> findByEmailOrId(String email, Long id);
    Void deleteByEmail(String email);
    public UserEntity save( UserEntity user );
    public Optional<UserEntity> findByEmail(String email);


}
