package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IMessageRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaMessageRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;




@Repository
public class MessageRepositoryImpl implements IMessageRepository {

    @Autowired
    JpaMessageRepository messageRepository;


    @Override
    public Optional<MessageEntity> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Page<MessageEntity> findByServerId(Long serverId, Pageable pageable) {
        return messageRepository.findByServerId(serverId, pageable);
    }

    @Override
    public MessageEntity save(MessageEntity message) {
        return messageRepository.save(message);
    }



}
