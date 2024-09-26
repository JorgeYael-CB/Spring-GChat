package com.yael.springboot.api.gchat.gchat.application.interfaces.services;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageWsDto;




public interface IMessageWs {

    public void sendMessageToClients( MessageWsDto message );

}
