package com.yael.springboot.api.gchat.gchat.domain.entities;

import jakarta.persistence.ManyToOne;



public class MessageEntity extends BaseEntity {

    private String content;
    private Boolean isEdited = false;


    @ManyToOne
    private ServerEntity server;

    @ManyToOne
    private UserEntity user;


    public MessageEntity(){}

    public MessageEntity(String content, ServerEntity server, UserEntity user) {
        this.content = content;
        this.server = server;
        this.user = user;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsEdited() {
        return isEdited;
    }

    public void setIsEdited(Boolean isEdited) {
        this.isEdited = isEdited;
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
