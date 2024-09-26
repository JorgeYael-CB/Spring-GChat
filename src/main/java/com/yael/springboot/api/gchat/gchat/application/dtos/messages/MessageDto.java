package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import com.yael.springboot.api.gchat.gchat.application.dtos.BaseDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



public class MessageDto extends BaseDto {
    private String content;
    private Boolean edited = false;
    private Boolean serverMessage = false;
    private ServerEntity server;
    private UserEntity user;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Boolean getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(Boolean serverMessage) {
        this.serverMessage = serverMessage;
    }

    public ServerEntity getServer() {
        return server;
    }

    public void setServer(ServerEntity server) {
        this.server = server;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
