package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.NewMessageDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
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
    IJwtService jwtService;
    @Autowired
    MessageMapper messageMapper;


    @Transactional
    public ResponseService<MessageDto> sendMessage( Long serverId, NewMessageDto messageDto ){
        Optional<ServerEntity> server = serverRepository.findById(serverId);
        UserEntity user = getUserByAuth.getUser();
        if( !server.isPresent() ) throw CustomException.notFoundException("Server not found");

        ServerEntity serverDb = server.get();
        MessageEntity message = new MessageEntity();
        message.setContent(messageDto.getContent());
        message.setServer(serverDb);
        message.setUser(user);

        serverDb.getMessages().add(message);

        messageRepository.save(message);

        ResponseService<MessageDto> response = new ResponseService<>();
        response.setData( messageMapper.messageEntityToMessageDto(message) );
        response.setDate(new Date());
        response.setStatus(201);

        return response;
    }

}
