package com.yael.springboot.api.gchat.gchat.application.interfaces.projections;

import java.util.List;




public interface IServerProjection extends IProjectionBase {

    int getUsersLimit();
    String getDescription();
    List<IUserPreviewProjection> getUsers();
    IUserPreviewProjection getOwner();
    IPhotoPreviewProjection getImage();

}
