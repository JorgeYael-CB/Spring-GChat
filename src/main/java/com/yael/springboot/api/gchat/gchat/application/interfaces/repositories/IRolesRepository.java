package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;




public interface IRolesRepository {

    public Optional<RoleEntity> findByRole(String role);

}
