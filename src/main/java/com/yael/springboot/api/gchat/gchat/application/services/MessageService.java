package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.NewMessageDto;
import com.yael.springboot.api.gchat.gchat.application.mappers.MessageMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.MessageRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.ServerRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;




@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ServerRepository serverRepository;
    @Autowired
    GetUserByAuth getUserByAuth;
    @Autowired
    MessageMapper messageMapper;


    @Transactional
    public ResponseService<MessageDto> sendMessage( Long serverId, NewMessageDto messageDto ){
        Optional<ServerEntity> server = serverRepository.findById(serverId);
        if( !server.isPresent() ) throw CustomException.notFoundException("Server not found");
        UserEntity user = getUserByAuth.getUser();

        ServerEntity serverDb = server.get();

        if( !serverDb.getUsers().stream().anyMatch( u -> u.getId().equals(user.getId()) ) ){
            throw CustomException.unaothorizedException("You cannot send messages to this server.");
        }

        MessageEntity message = new MessageEntity();
        message.setContent(messageDto.getContent());
        message.setServer(serverDb);
        message.setUser(user);

        serverDb.getMessages().add(message);
        user.getMessages().add(message);

        messageRepository.save(message);

        ResponseService<MessageDto> response = new ResponseService<>();
        response.setData( messageMapper.messageEntityToMessageDto(message) );
        response.setDate(new Date());
        response.setStatus(201);

        return response;
    }


    @Transactional(readOnly=true)
    public ResponseService<List<MessageDto>> getMessages( PaginationDto pagination, Long serverId ){
        return null;
    }

    @Transactional
    public ResponseService<MessageDto> updateMessage( Long messageId, NewMessageDto messageDto ){
        return null;
    }

    @Transactional
    public ResponseService<MessageDto> deleteMessage( Long messageId ){
        return null;
    }

}
