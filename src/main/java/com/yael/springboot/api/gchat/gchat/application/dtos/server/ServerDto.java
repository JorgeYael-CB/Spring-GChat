package com.yael.springboot.api.gchat.gchat.application.dtos.server;


import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.dtos.BaseDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;




public class ServerDto extends BaseDto {
    private List<UserDto> users;
    private UserDto owner;
    private int usersLimit = 20;
    private String description;
    private String image;


    public List<UserDto> getUsers() {
        return users;
    }
    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
    public UserDto getOwner() {
        return owner;
    }
    public void setOwner(UserDto owner) {
        this.owner = owner;
    }
    public int getUsersLimit() {
        return usersLimit;
    }
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
