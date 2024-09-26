package com.yael.springboot.api.gchat.gchat.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {

    @Column(nullable=false)
    private String content;
    private Boolean edited = false;
    private Boolean serverMessage = false;

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
        return edited;
    }

    public void setIsEdited(Boolean edited) {
        this.edited = edited;
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

    public Boolean getIsServerMessage() {
        return serverMessage;
    }

    public void setIsServerMessage(Boolean serverMessage) {
        this.serverMessage = serverMessage;
    }

}
