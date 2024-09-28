package com.yael.springboot.api.gchat.gchat.application.interfaces.projections;

import java.util.Date;



public interface IProjectionBase {

    Date getCreateAt();
    Date getUpdatedAt();
    Long getId();

}
