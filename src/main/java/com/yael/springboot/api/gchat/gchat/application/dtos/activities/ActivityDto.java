package com.yael.springboot.api.gchat.gchat.application.dtos.activities;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IActivityProjection;




public class ActivityDto implements IActivityProjection {

    private String activity;
    private Long id;


    public ActivityDto(){}


    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getActivity() {
        return activity;
    }

    @Override
    public Long getId() {
        return id;
    }

}
