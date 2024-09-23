package com.yael.springboot.api.gchat.gchat.application.services;


import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.LoginUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.mappers.UserMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IUserRepository;





@Service
public class AuthService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRolesRepository rolesRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Transactional(readOnly=true)
    public ResponseService<UserDto> Login( LoginUserDto loginUserDto ){
        //Validar que el usuario existe
        Optional<UserEntity> userDb = userRepository.findByEmail( loginUserDto.getEmail() );
        if( !userDb.isPresent() ) throw CustomException.notFoundException("User not exists");

        UserEntity user = userDb.get();

        if( !user.getIsActive() ) throw CustomException.unaothorizedException("the account has been deactivated.");

        Boolean isPasswordValid = passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword());
        if( !isPasswordValid ) throw CustomException.badRequestException("credentials are not correct");

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(user));
        response.setStatus(201);
        response.setToken("JWT");

        return response;
    }

    @Transactional
    public ResponseService<UserDto> Register( RegisterUserDto registerUserDto ){
        Optional<UserEntity> userDb = userRepository.findByEmailOrName(registerUserDto.getEmail(), registerUserDto.getName());
        if( userDb.isPresent() ) throw CustomException.badRequestException("Account already exists");

        Optional<RoleEntity> userRole = rolesRepository.findByRole("USER");
        String passwordBcrypt = passwordEncoder.encode(registerUserDto.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setName(registerUserDto.getName());
        newUser.setEmail(registerUserDto.getEmail());
        newUser.setPassword(passwordBcrypt);

        if( userRole.isPresent() ){
            newUser.setRoles( Arrays.asList(userRole.get()) );
        }

        userRepository.save(newUser);

        String token = "JWT";
        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(newUser));
        response.setStatus(201);
        response.setToken(token);

        return response;
    }

    public ResponseService<UserDto> updateUser(){
        return null;
    }

    public ResponseService<UserDto> refreshToken(){
        return null;
    }

}
