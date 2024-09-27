package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.NewMessageDto;
import com.yael.springboot.api.gchat.gchat.application.services.MessageService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;





@RestController
@RequestMapping("/messages")
public class MessageController {


    @Autowired
    MessageService messageService;


    @PostMapping("/{id}")
    public ResponseEntity<ResponseService<MessageDto>> newMessage(@PathVariable Long id, @RequestBody NewMessageDto messageDto) {
        var response = messageService.sendMessage(id, messageDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService<List<MessageDto>>> getMessages(@PathVariable Long id, @ModelAttribute PaginationDto paginationDto) {
        ResponseService<List<MessageDto>> response = messageService.getMessages(paginationDto, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
