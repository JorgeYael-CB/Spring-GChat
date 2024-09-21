package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;




@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
