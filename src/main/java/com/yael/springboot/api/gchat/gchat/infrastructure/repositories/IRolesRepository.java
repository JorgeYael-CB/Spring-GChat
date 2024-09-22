package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;


public interface IRolesRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole( String role );
}
