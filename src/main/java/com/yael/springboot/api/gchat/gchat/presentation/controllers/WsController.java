package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;




@Controller
public class WsController {


    @MessageMapping("/ws")
    @SendTo("/topic/messages")
    public MessageDto send( MessageDto message ) throws Exception {
        System.out.println("Alguien envio un mensaje... " + message.getContent());
        return message;
    }


}
