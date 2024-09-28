package com.yael.springboot.api.gchat.gchat.application.services;


import java.lang.reflect.Method;
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
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import com.yael.springboot.api.gchat.gchat.application.mappers.UserMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;





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
    @Autowired
    IFilesService filesService;
    @Autowired
    GetUserByAuth getUserByAuth;
    @Autowired
    IJwtService jwtService;


    @Transactional
    public ResponseService<UserDto> Register( RegisterUserDto registerUserDto ){
        if( userRepository.findByEmail(registerUserDto.getEmail()).isPresent() ) throw CustomException.badRequestException("User already exists");
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

        String token = this.jwtService.createToken(newUser.getRoles(), newUser.getEmail());
        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(newUser));
        response.setStatus(201);
        response.setToken(token);

        return response;
    }


    @Transactional
    public ResponseService<UserDto> updateUser( UpdateUserDto user ){
        // Validamos que tenga al menos un campo
        Method[] methods = UpdateUserDto.class.getDeclaredMethods();
        Boolean isAllMethodsEmpty = true;

        for (Method meth : methods) {
            if( meth.getName().startsWith("get") ){

                try {
                    Object value = meth.invoke(user);
                    if( value != null ){
                        isAllMethodsEmpty = false;
                        break;
                    }
                } catch (Exception e) {}
            }
        }

        if( isAllMethodsEmpty ){
            throw CustomException.badRequestException("Missing fields to updated");
        }

        Optional<UserEntity> userOptional = userRepository.findByEmail(getUserByAuth.getUsernameLogged());
        if( !userOptional.isPresent() ) throw CustomException.notFoundException("User not exists");

        UserEntity userDb = userOptional.get();
        if (user.getAge() != null) userDb.setAge(user.getAge());
        if (user.getCountry() != null) userDb.setCountry(user.getCountry());
        if (user.getDescription() != null) userDb.setDescription(user.getDescription());
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

        String token = this.jwtService.createToken(userDb.getRoles(), userDb.getEmail());

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(userDb));
        response.setStatus(200);
        response.setToken(token);

        return response;
    }


    @Transactional(readOnly=true)
    public ResponseService<UserDto> getUser( Long id, String email ){
        UserEntity userDb = userRepository.findByEmailOrId(email, id)
            .orElseThrow( () -> CustomException.notFoundException("User not exist"));

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(userDb));
        response.setStatus(200);
        response.setDate(new Date());

        return response;
    }


    public ResponseService<UserDto> getUserByToken(){
        UserEntity user = getUserByAuth.getUser();
        String token = jwtService.createToken(user.getRoles(), user.getEmail());

        return new ResponseService<>(new Date(), userMapper.userEntityToUserDto(user), 200, token);
    }


    @Transactional
    public ResponseService<Boolean> deleteAccount(){
        String email = getUserByAuth.getUsernameLogged();
        userRepository.deleteByEmail(email);

        return new ResponseService<>(new Date(), true, 200);
    }

}
