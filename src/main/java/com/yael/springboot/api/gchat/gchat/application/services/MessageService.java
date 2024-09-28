package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageWsDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.NewMessageDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.messages.EnumTypeMessage;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMessageWs;
import com.yael.springboot.api.gchat.gchat.application.mappers.MessageMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IRolesRepository;
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
    @Autowired
    IMessageWs messageWs;
    @Autowired
    IRolesRepository rolesRepository;


    @Transactional
    public ResponseService<Boolean> sendMessage(Long serverId, NewMessageDto messageDto) {
        ServerEntity serverDb = serverRepository.findById(serverId)
            .orElseThrow(() -> CustomException.notFoundException("Server not found"));

        UserEntity user = getUserByAuth.getUser();

        if (!serverDb.getUsers().contains(user)) {
            throw CustomException.unaothorizedException("You cannot send messages to this server.");
        }

        MessageEntity message = new MessageEntity();
        message.setContent(messageDto.getContent());
        message.setServer(serverDb);
        message.setUser(user);

        messageRepository.save(message);

        // Notificar a los usuarios.
        EnumTypeMessage type = EnumTypeMessage.NEW_MESSAGE;
        MessageWsDto msg = messageMapper.messageEntityToMessageWs(message, type);
        messageWs.sendMessageToClients(msg);

        return new ResponseService<>(new Date(), true, 201);
    }


    @Transactional(readOnly=true)
    public ResponseServicePagination<List<MessageDto>> getMessages( PaginationDto pagination, Long serverId ){
        if( !serverRepository.findById(serverId).isPresent() ) throw CustomException.notFoundException("Server not found");
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(Sort.Direction.DESC, "id"));

        Page<MessageEntity> messages = messageRepository.findByServerId(serverId, pageable);
        List<MessageDto> messageDtos = messages.stream().map( m -> messageMapper.messageEntityToMessageDto(m)).collect(Collectors.toList());

        ResponseServicePagination<List<MessageDto>> response = new ResponseServicePagination<>();
        response.setStatus(200);
        response.setData(messageDtos);
        response.setCurrentPage(pagination.getPage());
        response.setMaxPage(messages.getTotalPages() - 1);

        return response;
    }

    @Transactional
    public ResponseService<Boolean> updateMessage( Long messageId, NewMessageDto messageDto ){
        MessageEntity messageDb = messageRepository.findById(messageId)
            .orElseThrow( () -> CustomException.notFoundException("Message to edit not found"));

        UserEntity user = getUserByAuth.getUser();

        if( !messageDb.getUser().getId().equals(user.getId()) ) throw CustomException.unaothorizedException("Only the person who sent the message can update it");
        messageDb.setContent(messageDto.getContent());
        messageDb.setIsEdited(true);

        // Notificacion a los usuarios del mensaje actualizado.
        EnumTypeMessage type = EnumTypeMessage.UPDATE_MESSAGE;
        MessageWsDto msg = messageMapper.messageEntityToMessageWs(messageDb, type);
        messageWs.sendMessageToClients(msg);

        return new ResponseService<>(new Date(), true, 200);
    }

    @Transactional
    public ResponseService<Boolean> deleteMessage( Long messageId ){
        MessageEntity message = messageRepository.findById(messageId)
            .orElseThrow( () -> CustomException.notFoundException("Message not found to deleted"));
        UserEntity user = getUserByAuth.getUser();
        RoleEntity roleAdmin = rolesRepository.findByRole("ROLE_ADMIN").get();

        if( !user.getRoles().contains(roleAdmin) && !message.getUser().getId().equals(user.getId()) ){
            throw CustomException.unaothorizedException("only the creator can delete the message.");
        }

        message.setIsActive(false);

        EnumTypeMessage type = EnumTypeMessage.DELETE_MESSAGE;
        MessageWsDto msg = messageMapper.messageEntityToMessageWs(message, type);
        messageWs.sendMessageToClients(msg);

        return new ResponseService<>(new Date(), true, 200);
    }

}
