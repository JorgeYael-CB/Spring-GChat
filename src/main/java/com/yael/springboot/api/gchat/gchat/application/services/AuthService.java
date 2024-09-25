package com.yael.springboot.api.gchat.gchat.application.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UpdateUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.application.mappers.UserMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.UserRepository;





@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IRolesRepository rolesRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IFilesService filesService;


    @Transactional
    public ResponseService<UserDto> Register( RegisterUserDto registerUserDto ){
        Optional<RoleEntity> userRole = rolesRepository.findByRole("ROLE_USER");
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


    @Transactional
    public ResponseService<UserDto> updateUser( UpdateUserDto user ){
        Optional<UserEntity> userOptional = userRepository.findById(user.getUserId());
        if( !userOptional.isPresent() ) throw CustomException.notFoundException("User not exists");

        UserEntity userDb = userOptional.get();
        if (user.getAge() != null) userDb.setAge(user.getAge());
        if (user.getCountry() != null) userDb.setCountry(user.getCountry());
        userDb.setUpdatedAt(new Date());
        if (user.getDescription() != null) userDb.setDescription(user.getDescription());
        if (user.getEmail() != null) userDb.setEmail(user.getEmail());
        if (user.getName() != null) userDb.setName(user.getName());

        if( user.getImages() != null && !user.getImages().isEmpty() ){
            List<PhotoEntity> images = new ArrayList<>();

            user.getImages().forEach(img -> {
                String imgUrl = filesService.saveImage(img, "images/");

                PhotoEntity photoEntity = new PhotoEntity();
                photoEntity.setImage(imgUrl);
                images.add(photoEntity);
            });

            Stream.concat(images.stream(), userDb.getImages().stream());
            userDb.setImages(images);
        }

        if( user.getProfileImage() != null ){
            if( userDb.getProfileImage() != null ) filesService.deleteImage(userDb.getProfileImage().getImage(), "images/");
            String imgUrl = filesService.saveImage(user.getProfileImage(), "images/");

            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setImage(imgUrl);

            userDb.setProfileImage(photoEntity);
        }

        userRepository.save(userDb);

        String token = "JWT";

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(userDb));
        response.setStatus(200);
        response.setToken(token);

        return response;
    }


    public ResponseService<UserDto> refreshToken(){
        return null;
    }

}
