package com.yael.springboot.api.gchat.gchat.application.interfaces.projections;

import java.util.List;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;




public interface IUserAuthProjection extends IProjectionBase {

    List<RoleEntity> getRoles();
    int getAge();
    String getCountry();
    String getCity();
    String getEmail();
    String getDescription();
    String getName();
    List<IPhotoPreviewProjection> getImages();
    IPhotoPreviewProjection getProfileImage();
    List<IServerPreviewProjection> getServers();

}
