package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaRolesRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;


public class RolesRepositoryImpl implements IRolesRepository {

    @Autowired
    JpaRolesRepository jpaRepository;


    @Override
    public Optional<RoleEntity> findByRole(String role) {
        return jpaRepository.findByRole(role);
    }

}
