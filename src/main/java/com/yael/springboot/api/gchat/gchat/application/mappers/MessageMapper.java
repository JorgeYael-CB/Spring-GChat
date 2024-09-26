package com.yael.springboot.api.gchat.gchat.application.mappers;

import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageWsDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.messages.EnumTypeMessage;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;



@Component
public class MessageMapper {

    public MessageDto messageEntityToMessageDto( MessageEntity message ){
        MessageDto messageDto = new MessageDto();

        messageDto.setContent(message.getContent());
        messageDto.setCreateAt(message.getCreateAt());
        messageDto.setEdited(message.getIsEdited());
        messageDto.setId(message.getId());
        messageDto.setIsActive(message.getIsActive());
        messageDto.setServerId(message.getServer().getId());
        messageDto.setUser(message.getUser());
        messageDto.setServerMessage(message.getIsServerMessage());
        messageDto.setUpdatedAt(message.getUpdatedAt());

        return messageDto;
    }

    public MessageWsDto messageEntityToMessageWs( MessageEntity message, EnumTypeMessage typeMessage ){
        MessageWsDto messageWs = new MessageWsDto();
        MessageDto messageDto = this.messageEntityToMessageDto(message);

        messageWs.setMessageDto(messageDto);
        messageWs.setType(typeMessage);

        return messageWs;
    }

}
