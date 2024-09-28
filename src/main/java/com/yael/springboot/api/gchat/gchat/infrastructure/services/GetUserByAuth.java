package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;




@Service
public class GetUserByAuth {

    @Autowired
    IUserRepository userRepository;


    @Transactional(readOnly = true)
    public UserEntity getUser(){
        final String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(principal)
            .orElseThrow( () -> CustomException.notFoundException("user not found"));
    }

    public String getUsernameLogged(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
