package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.UserRepository;




@Service
public class GetUserByAuth {

    @Autowired
    UserRepository userRepository;


    @Transactional(readOnly = true)
    public UserEntity getUser(){
        final String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<UserEntity> user = userRepository.findByEmail(principal);
        if( !user.isPresent() ) throw CustomException.badRequestException("User not existys");

        return user.get();
    }

    public String getUsernameLogged(){
        final String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }

}
