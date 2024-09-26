package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import com.yael.springboot.api.gchat.gchat.application.interfaces.messages.EnumTypesMessages;




public class MessageWsDto {

    private MessageDto messageDto;
    private EnumTypesMessages type;


    public EnumTypesMessages getType() {
        return type;
    }
    public void setType(EnumTypesMessages type) {
        this.type = type;
    }
    public MessageDto getMessageDto() {
        return messageDto;
    }
    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }
}
