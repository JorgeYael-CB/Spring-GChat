package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import com.yael.springboot.api.gchat.gchat.application.interfaces.Enums.EnumTypeMessage;




public class MessageWsDto {

    private MessageDto messageDto;
    private EnumTypeMessage type;


    public EnumTypeMessage getType() {
        return type;
    }
    public void setType(EnumTypeMessage type) {
        this.type = type;
    }
    public MessageDto getMessageDto() {
        return messageDto;
    }
    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }
}
