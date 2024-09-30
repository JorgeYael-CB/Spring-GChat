package com.yael.springboot.api.gchat.gchat.application.interfaces.projections;





public interface IMessageProjection extends IProjectionBase {

    String getContent();
    Boolean getEdited();
    Boolean getServerMessage();
    IUserPreviewProjection getUser();

}
