package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserPreviewProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;




public class UserPreviewDto implements IUserPreviewProjection {

    private IPhotoPreviewProjection profileimage;
    private String name;
    private Long id;
    private List<RoleEntity> roles;


    public UserPreviewDto(){};


    public void setProfileimage(IPhotoPreviewProjection profileimage) {
        this.profileimage = profileimage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public IPhotoPreviewProjection getProfileImage() {
        return profileimage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roles;
    }

}
