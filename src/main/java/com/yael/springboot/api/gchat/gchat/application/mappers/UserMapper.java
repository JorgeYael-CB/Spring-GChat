package com.yael.springboot.api.gchat.gchat.application.mappers;


import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;


@Component
public class UserMapper {

    public UserDto userEntityToUserDto( UserEntity user ){
        UserDto userDto = new UserDto();

        userDto.setActivities(user.getActivities());
        userDto.setAge(user.getAge());
        userDto.setCountry(user.getCountry());
        userDto.setDescription(user.getDescription());
        userDto.setEmail(user.getEmail());
        userDto.setImages(user.getImages());
        userDto.setName(user.getName());
        userDto.setPreferences(user.getPreferences());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setRoles(user.getRoles());
        userDto.setServers(user.getServers());

        return userDto;
    }

}
