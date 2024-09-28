package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaUserRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    JpaUserRepository jpaUserRepository;


    @Override
    public Optional<UserEntity> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByNameOrEmail(String name, String email) {
        return jpaUserRepository.findByNameOrEmail(name, email);
    }

    @Override
    public Optional<UserEntity> findByEmailOrId(String email, Long id) {
        return jpaUserRepository.findByEmailOrId(email, id);
    }

    @Override
    public Void deleteByEmail(String email) {
        return jpaUserRepository.deleteByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<IUserAuthProjection> findUserById(Long id) {
        return jpaUserRepository.findUserById(id);
    }



}

