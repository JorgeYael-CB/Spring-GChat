package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;




@Repository
public interface ServerRepository extends JpaRepository<ServerEntity, Long> {}
