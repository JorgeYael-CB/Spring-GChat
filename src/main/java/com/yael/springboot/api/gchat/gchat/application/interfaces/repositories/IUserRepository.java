package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



public interface IUserRepository {


    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByNameOrEmail(String name, String email);
    Optional<UserEntity> findByEmailOrId(String email, Long id);
    Void deleteByEmail(String email);
    UserEntity save( UserEntity user );
    Optional<UserEntity> findByEmail(String email);
    Optional<IUserAuthProjection> findUserById(Long id);


}
