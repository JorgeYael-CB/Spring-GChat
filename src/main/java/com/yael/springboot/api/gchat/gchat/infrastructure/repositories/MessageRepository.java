package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;





public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    public Page<MessageEntity> findByServerId( Long serverId, Pageable pageable );

}
