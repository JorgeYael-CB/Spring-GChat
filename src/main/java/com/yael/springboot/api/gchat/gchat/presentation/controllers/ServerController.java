package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;
import com.yael.springboot.api.gchat.gchat.application.services.ServerService;




@RestController
@RequestMapping("/api/servers")
public class ServerController {

    @Autowired
    ServerService serverService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseService<IServerProjection>> create(){
        ResponseService<IServerProjection> response = serverService.create();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<IServerProjection>> delete( @PathVariable Long id ){
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseService<IServerProjection>> update( @PathVariable Long id ){
        return null;
    }


    @GetMapping("/join-random")
    public ResponseEntity<ResponseService<IServerProjection>> joinRandom(){
        ResponseService<IServerProjection> response = serverService.joinRandom();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/join/{id}")
    public ResponseEntity<?> joinById( @PathVariable Long id ){
        ResponseService<IServerProjection> response = serverService.joinById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService<IServerProjection>> getServer( @PathVariable Long id ){
        ResponseService<IServerProjection> response = serverService.getServerDetails(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/leave/{id}")
    public ResponseEntity<ResponseService<IServerProjection>> deleteServer( @PathVariable Long id ){
        return null;
    }

}
