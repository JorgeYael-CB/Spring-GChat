package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PreferencesEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;


public class UserDto {
    private String name;
    private String email;
    private String description;
    private int age;
    private String country;
    private List<RoleEntity> roles;
    private PhotoEntity profileImage;
    private List<PhotoEntity> images;
    private List<ActivityEntity> activities;
    private List<ServerDto> servers;
    private List<ServerDto> serverOwners;
    private PreferencesEntity preferences;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public List<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
    public PhotoEntity getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(PhotoEntity profileImage) {
        this.profileImage = profileImage;
    }
    public List<PhotoEntity> getImages() {
        return images;
    }
    public void setImages(List<PhotoEntity> images) {
        this.images = images;
    }
    public List<ActivityEntity> getActivities() {
        return activities;
    }
    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }
    public List<ServerDto> getServers() {
        return servers;
    }
    public void setServers(List<ServerDto> servers) {
        this.servers = servers;
    }
    public List<ServerDto> getServerOwners() {
        return serverOwners;
    }
    public void setServerOwners(List<ServerDto> serverOwners) {
        this.serverOwners = serverOwners;
    }
    public PreferencesEntity getPreferences() {
        return preferences;
    }
    public void setPreferences(PreferencesEntity preferences) {
        this.preferences = preferences;
    }

}
