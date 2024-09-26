package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMessageWs;




public class MessgeWsImpl implements IMessageWs{

    private final SimpMessagingTemplate messagingTemplate;


    public MessgeWsImpl( SimpMessagingTemplate messagingTemplate ){
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void sendMessageToClients(MessageDto message){
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
