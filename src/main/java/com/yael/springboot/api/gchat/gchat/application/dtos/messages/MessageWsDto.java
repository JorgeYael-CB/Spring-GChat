package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import com.yael.springboot.api.gchat.gchat.application.interfaces.Enums.EnumTypeMessage;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IMessageProjection;




public class MessageWsDto {

    private IMessageProjection messageDto;
    private EnumTypeMessage type;


    public EnumTypeMessage getType() {
        return type;
    }
    public void setType(EnumTypeMessage type) {
        this.type = type;
    }
    public IMessageProjection getMessageDto() {
        return messageDto;
    }
    public void setMessageDto(IMessageProjection messageDto) {
        this.messageDto = messageDto;
    }
}
