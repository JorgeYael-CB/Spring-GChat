package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;
import com.yael.springboot.api.gchat.gchat.application.services.ServerService;




@RestController
@RequestMapping("/api/servers")
public class ServerController {

    @Autowired
    ServerService serverService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(){
        ResponseService<ServerDto> response = serverService.create();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(){
        return null;
    }

    @GetMapping("/join-random")
    public ResponseEntity<ResponseService<ServerDto>> joinRandom(){
        ResponseService<ServerDto> response = serverService.joinRandom();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/join/{id}")
    public ResponseEntity<?> joinById( @PathVariable Long id ){
        ResponseService<ServerDto> response = serverService.joinById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServer( @PathVariable Long id ){
        return null;
    }

}
