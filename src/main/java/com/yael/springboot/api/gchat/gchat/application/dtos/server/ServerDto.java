package com.yael.springboot.api.gchat.gchat.application.dtos.server;

import java.util.Date;
import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserPreviewProjection;




public class ServerDto implements IServerProjection {


    private Date createat;
    private Date updatedat;
    private Long id;
    private int userslimit;
    private String description;
    private List<IUserPreviewProjection> users;
    private IUserPreviewProjection owner;
    private IPhotoPreviewProjection image;



    public ServerDto() {}


    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserslimit(int userslimit) {
        this.userslimit = userslimit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsers(List<IUserPreviewProjection> users) {
        this.users = users;
    }

    public void setOwner(IUserPreviewProjection owner) {
        this.owner = owner;
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
    public int getUsersLimit() {
        return userslimit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<IUserPreviewProjection> getUsers() {
        return users;
    }

    @Override
    public IUserPreviewProjection getOwner() {
        return owner;
    }

    @Override
    public IPhotoPreviewProjection getImage() {
        return image;
    }

}
