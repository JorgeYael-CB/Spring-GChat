package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;




public class ServerEntity extends BaseEntity {

    private int usersLimit = 20;

    // ManyToMany
    private List<UserEntity> users = new ArrayList<>();;

    // ManyToOne
    private UserEntity owner;

    // oneToMany
    private List<MessageEntity> messages = new ArrayList<>();;



    public int getUsersLimit() {
        return usersLimit;
    }

    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

}
