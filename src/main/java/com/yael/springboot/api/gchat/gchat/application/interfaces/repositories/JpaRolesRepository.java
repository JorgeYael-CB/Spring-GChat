package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;




public interface JpaRolesRepository extends JpaRepository<RoleEntity, Long> {
    public Optional<RoleEntity> findByRole(String role);
}
