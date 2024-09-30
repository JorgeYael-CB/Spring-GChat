package com.yael.springboot.api.gchat.gchat.application.dtos.server;

import java.util.Date;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerPreviewProjection;




public class ServerPreviewDto implements IServerPreviewProjection {

    private Date createat;
    private Date updatedat;
    private Long id;
    private IPhotoPreviewProjection image;

    public ServerPreviewDto(){}


    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(IPhotoPreviewProjection image) {
        this.image = image;
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
    public IPhotoPreviewProjection getImage() {
        return image;
    }
}
