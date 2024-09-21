package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToMany;




public class PreferencesEntity extends BaseEntity {

    private int maxAge = 60;
    private int minAge = 18;
    private String country;

    @ManyToMany
    private List<ActivityEntity> activities = new ArrayList<>();



    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

}
