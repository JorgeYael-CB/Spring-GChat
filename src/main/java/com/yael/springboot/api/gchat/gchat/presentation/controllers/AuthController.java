package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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




@CrossOrigin(origins = "*") // solo para pruebas
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println(username);
        } else {
            String username = principal.toString();
            System.out.println(username);
        }

        ResponseService<UserDto> response = authService.updateUser(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
