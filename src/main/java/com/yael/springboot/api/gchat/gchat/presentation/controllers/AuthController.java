package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.LoginUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.services.AuthService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ResponseService<UserDto>> login( @Valid @RequestBody LoginUserDto loginUserDto ){
        ResponseService<UserDto> response = authService.Login(loginUserDto);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseService<UserDto>> register( @Valid @ModelAttribute RegisterUserDto registerUserDto ){
        ResponseService<UserDto> response = authService.Register(registerUserDto);

        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
