package com.yael.springboot.api.gchat.gchat.application.dtos.likes;

import java.util.Date;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserPreviewProjection;



public class LikeDto implements ILikeProjection {

    private Date createat;
    private Date updatedat;
    private Long id;
    private IUserPreviewProjection user;


    public LikeDto(){}


    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public void setId(Long id) {
        this.id = id;
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
    public IUserPreviewProjection getUser() {
        return user;
    }



}
