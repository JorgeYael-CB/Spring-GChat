package com.yael.springboot.api.gchat.gchat.application.dtos.server;


import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;




public class ServerDto {
    private Long id;

    private List<MessageEntity> messages;
    private List<UserDto> users;
    private UserDto owner;
    private int usersLimit = 20;
    private String description;
    private String image;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<MessageEntity> getMessages() {
        return messages;
    }
    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }
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
