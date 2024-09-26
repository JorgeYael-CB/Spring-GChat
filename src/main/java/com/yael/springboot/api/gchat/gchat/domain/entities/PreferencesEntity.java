package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "preferences")
public class PreferencesEntity extends BaseEntity {

    private String country;
    private String city;

    @ManyToMany
    private List<ActivityEntity> activities = new ArrayList<>();


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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

}
