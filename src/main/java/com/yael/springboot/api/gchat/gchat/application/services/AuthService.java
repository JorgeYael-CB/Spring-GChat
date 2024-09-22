package com.yael.springboot.api.gchat.gchat.application.services;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.LoginUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.application.mappers.UserMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IActivityRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.IUserRepository;





@Service
public class AuthService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IActivityRepository activityRepository;

    @Autowired
    IRolesRepository rolesRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IFilesService fileService;


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

        UserEntity newUser = new UserEntity();
        newUser.setName(registerUserDto.getName());
        newUser.setEmail(registerUserDto.getEmail());
        newUser.setPassword(registerUserDto.getPassword());

        PhotoEntity profileImage = null;

        if( registerUserDto.getProfileImage() != null ){
            String fileUrl = fileService.saveImage(registerUserDto.getProfileImage(), "images/");
            profileImage = new PhotoEntity();

            profileImage.setImage(fileUrl);
        }

        if( registerUserDto.getActivities() != null && registerUserDto.getActivities().toArray().length > 0 ){
            List<ActivityEntity> activities = activityRepository.findAll();

            registerUserDto.getActivities().forEach( a -> {
                System.out.println(a);
            });
        }

        if( profileImage != null ){
            newUser.setProfileImage(profileImage);
        }

        String passwordBcrypt = passwordEncoder.encode(registerUserDto.getPassword());

        newUser.setAge(registerUserDto.getAge());
        newUser.setDescription(registerUserDto.getDescription());
        newUser.setCountry(registerUserDto.getCountry());
        newUser.setPassword(passwordBcrypt);

        if( userRole.isPresent() ){
            newUser.setRoles( Arrays.asList(userRole.get()) );
        }

        String token = "JWT";

        ResponseService<UserDto> response = new ResponseService<>();
        response.setData(userMapper.userEntityToUserDto(newUser));
        response.setStatus(201);
        response.setToken(token);

        UserEntity userDb2 = userRepository.save(newUser);
        System.out.println(userDb2);

        return response;
    }

    public ResponseService<UserDto> updateUser(){
        return null;
    }

    public ResponseService<UserDto> refreshToken(){
        return null;
    }

}
