package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;




public interface IMessageRepository {

    public Optional<MessageEntity> findById(Long id);
    public Page<MessageEntity> findByServerId( Long serverId, Pageable pageable );
    public MessageEntity save(MessageEntity message);

}
