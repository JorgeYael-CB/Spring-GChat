package com.yael.springboot.api.gchat.gchat.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "activities")
public class ActivityEntity extends BaseEntity {
    private String activity;


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
