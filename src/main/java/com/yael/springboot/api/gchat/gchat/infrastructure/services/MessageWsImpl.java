package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageWsDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMessageWs;



@Service
public class MessageWsImpl implements IMessageWs{

    private final SimpMessagingTemplate messagingTemplate;


    public MessageWsImpl( SimpMessagingTemplate messagingTemplate ){
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void sendMessageToClients(MessageWsDto message){
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
