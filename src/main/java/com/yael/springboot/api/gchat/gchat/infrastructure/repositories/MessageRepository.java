package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;





public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    public List<MessageEntity> findByServerId( Long serverId, Pageable pageable );

}
