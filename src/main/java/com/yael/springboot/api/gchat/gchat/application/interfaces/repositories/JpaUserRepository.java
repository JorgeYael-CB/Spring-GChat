package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;




public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNameOrEmail(String name, String email);
    Optional<UserEntity> findByEmailOrId(String email, Long id);
    Void deleteByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    Optional<IUserAuthProjection> findUserById(Long id);

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?2 or u.email = ?1")
    Optional<IUserAuthProjection> findUserByEmailOrId(String email, Long id);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 or u.name = ?2")
    Optional<IUserAuthProjection> findUserByEmailOrName(String email, String name);
}
