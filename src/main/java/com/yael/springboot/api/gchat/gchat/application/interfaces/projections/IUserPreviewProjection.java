package com.yael.springboot.api.gchat.gchat.application.interfaces.projections;

import java.util.List;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;



public interface IUserPreviewProjection {

    IPhotoPreviewProjection getProfileImage();
    String getName();
    Long getId();
    List<RoleEntity> getRoles();

}
