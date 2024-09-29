package com.yael.springboot.api.gchat.gchat.application.services;


import java.util.ArrayList;
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
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IValidateClassService;
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
    @Autowired
    IValidateClassService validateClassService;


    @Transactional
    public ResponseService<IUserAuthProjection> Register( RegisterUserDto registerUserDto ){
        if( userRepository.findUserByEmailOrName(registerUserDto.getEmail(), registerUserDto.getName()).isPresent() ) throw CustomException.badRequestException("User already exists");
        Optional<RoleEntity> userRole = rolesRepository.findByRole("ROLE_USER");
        String passwordBcrypt = passwordEncoder.encode(registerUserDto.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setName(registerUserDto.getName());
        newUser.setEmail(registerUserDto.getEmail());
        newUser.setPassword(passwordBcrypt);

        if( userRole.isPresent() ) newUser.getRoles().add(userRole.get());

        userRepository.save(newUser);

        String token = this.jwtService.createToken(newUser.getRoles(), newUser.getEmail());
        ResponseService<IUserAuthProjection> response = new ResponseService<>();
        response.setData(userRepository.findUserById(newUser.getId()).get());
        response.setStatus(201);
        response.setToken(token);

        return response;
    }


    @Transactional
    public ResponseService<UserDto> updateUser( UpdateUserDto user ){
        Boolean isAllMethodsEmpty = validateClassService.fieldsEmptyClass(UpdateUserDto.class, user);
        if( isAllMethodsEmpty ) throw CustomException.badRequestException("Missing fields to updated");

        UserEntity userDb = userRepository.findByEmail(getUserByAuth.getUsernameLogged())
            .orElseThrow( () -> CustomException.notFoundException("User not found"));

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

        String token = this.jwtService.createToken(userDb.getRoles(), userDb.getEmail());

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(userDb));
        response.setStatus(200);
        response.setToken(token);

        return response;
    }


    @Transactional(readOnly=true)
    public ResponseService<IUserAuthProjection> getUser( Long id, String email ){
        IUserAuthProjection userDb = userRepository.findUserByEmailOrId(email, id)
            .orElseThrow( () -> CustomException.notFoundException("User not exist"));

        ResponseService<IUserAuthProjection> response = new ResponseService<>();
        response.setData(userDb);
        response.setStatus(200);
        response.setDate(new Date());

        return response;
    }


    public ResponseService<IUserAuthProjection> getUserByToken(){
        IUserAuthProjection user = getUserByAuth.getUserProjection();
        String token = jwtService.createToken(user.getRoles(), user.getEmail());

        return new ResponseService<>(new Date(), user, 200, token);
    }


    @Transactional
    public ResponseService<Boolean> deleteAccount(){
        String email = getUserByAuth.getUsernameLogged();
        userRepository.deleteByEmail(email);

        return new ResponseService<>(new Date(), true, 200);
    }

}
