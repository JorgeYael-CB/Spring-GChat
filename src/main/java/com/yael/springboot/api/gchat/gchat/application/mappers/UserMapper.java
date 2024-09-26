package com.yael.springboot.api.gchat.gchat.application.mappers;


import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserServerDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;


@Component
public class UserMapper {

    public UserDto userEntityToUserDto( UserEntity user ){
        UserDto userDto = new UserDto();

        userDto.setActivities(user.getActivities());
        userDto.setAge(user.getAge());
        userDto.setCountry(user.getCountry());
        userDto.setDescription(user.getDescription());
        userDto.setImages(user.getImages());
        userDto.setName(user.getName());
        userDto.setPreferences(user.getPreferences());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setRoles(user.getRoles());
        userDto.setIsActive(user.getIsActive());
        userDto.setCreateAt(user.getCreateAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        userDto.setServers(
            user.getServers().stream()
                .map( u -> {
                    var server = new UserServerDto();
                    server.setId(u.getId());

                    server.setImage(
                        (u.getImage() != null)
                        ? u.getImage().getImage()
                        : null
                    );
                    return server;
                }).collect(Collectors.toList())
        );

        if( user.getEmail() != null ){
            userDto.setEmail(user.getEmail());
        }

        return userDto;
    }

}
