package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique=true)
    private String name;

    @Column(unique=true)
    private String email;

    private String password;
    private String description;
    private Integer age = 18;
    private String country;


    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
        name="user_roles", // tabla intermedia
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"),
        uniqueConstraints= { @UniqueConstraint(columnNames={"user_id", "role_id"}) }
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToOne(cascade=CascadeType.ALL)
    private PhotoEntity profileImage;

    @OneToMany(cascade=CascadeType.ALL)
    private List<PhotoEntity> images = new ArrayList<>();

    @ManyToMany
    private List<ActivityEntity> activities = new ArrayList<>();

    @ManyToMany
    private List<ServerEntity> servers = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<LikeEntity> likes = new ArrayList<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<ServerEntity> getServers() {
        return servers;
    }

    public void setServers(List<ServerEntity> servers) {
        this.servers = servers;
    }


    public List<LikeEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeEntity> likes) {
        this.likes = likes;
    }

}
