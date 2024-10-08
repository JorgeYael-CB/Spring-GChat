package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import java.util.Date;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IMessageProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserPreviewProjection;






public class MessageDto implements IMessageProjection {


    private Date createat;
    private Date updatedat;
    private Long id;
    private String content;
    private Boolean edited;
    private Boolean serverMessage;
    private IUserPreviewProjection user;


    public MessageDto(){}


    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public void setServerMessage(Boolean serverMessage) {
        this.serverMessage = serverMessage;
    }

    public void setUser(IUserPreviewProjection user) {
        this.user = user;
    }

    @Override
    public Date getCreateAt() {
        return createat;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedat;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Boolean getEdited() {
        return edited;
    }

    @Override
    public Boolean getServerMessage() {
        return serverMessage;
    }

    @Override
    public IUserPreviewProjection getUser() {
        return user;
    }

}
