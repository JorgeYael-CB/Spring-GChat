package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import java.util.Date;
import java.util.List;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerPreviewProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;





public class UserDto implements IUserAuthProjection {


    private Date createat;
    private Date updatedat;
    private Long id;
    private List<RoleEntity> roles;
    private int age;
    private String country;
    private String city;
    private String email;
    private String description;
    private String name;
    private List<IPhotoPreviewProjection> images;
    private IPhotoPreviewProjection profileimage;
    private List<IServerPreviewProjection> servers;


    public UserDto(){}


    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(List<IPhotoPreviewProjection> images) {
        this.images = images;
    }

    public void setProfileimage(IPhotoPreviewProjection profileimage) {
        this.profileimage = profileimage;
    }

    public void setServers(List<IServerPreviewProjection> servers) {
        this.servers = servers;
    }

    @Override
    public Date getCreateAt() {
        return createat;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedat;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roles;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IPhotoPreviewProjection> getImages() {
        return images;
    }

    @Override
    public IPhotoPreviewProjection getProfileImage() {
        return profileimage;
    }

    @Override
    public List<IServerPreviewProjection> getServers() {
        return servers;
    }
    
}
