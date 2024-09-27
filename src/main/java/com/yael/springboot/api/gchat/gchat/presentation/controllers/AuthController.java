package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UpdateUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.services.AuthService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseService<UserDto>> register( @Valid @RequestBody RegisterUserDto registerUserDto ){
        ResponseService<UserDto> response = authService.Register(registerUserDto);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseService<UserDto>> updateUser( @Valid @ModelAttribute UpdateUserDto entity) {
        ResponseService<UserDto> response = authService.updateUser(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-user")
    public ResponseEntity<ResponseService<UserDto>> postMethodName(
        @Param("email") String email,
        @Param("id") Long id
    ) {

        ResponseService<UserDto> response = authService.getUser(id, email);
        return ResponseEntity.status(200).body(response);
    }



}
