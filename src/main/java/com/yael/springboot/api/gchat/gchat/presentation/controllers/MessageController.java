package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.NewMessageDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IMessageProjection;
import com.yael.springboot.api.gchat.gchat.application.services.MessageService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseServicePagination;





@RestController
@RequestMapping("/api/messages")
public class MessageController {


    @Autowired
    MessageService messageService;


    @PostMapping("/{id}")
    public ResponseEntity<ResponseService<Boolean>> newMessage(@PathVariable Long id, @RequestBody NewMessageDto messageDto) {
        var response = messageService.sendMessage(id, messageDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseServicePagination<List<IMessageProjection>>> getMessages(@PathVariable Long id, @ModelAttribute PaginationDto paginationDto) {
        ResponseServicePagination<List<IMessageProjection>> response = messageService.getMessages(paginationDto, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseService<Boolean>> updateMessage( @PathVariable Long id, @RequestBody NewMessageDto messageDto ){
        ResponseService<Boolean> response = messageService.updateMessage(id, messageDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<Boolean>> deleteMessage( @PathVariable Long id ){
        var response = messageService.deleteMessage(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
