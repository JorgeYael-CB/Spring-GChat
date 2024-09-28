package com.yael.springboot.api.gchat.gchat.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "likes")
public class LikeEntity extends BaseEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="image_id")
    @JsonIgnore
    private PhotoEntity image;


    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public PhotoEntity getImage() {
        return image;
    }
    public void setImage(PhotoEntity image) {
        this.image = image;
    }

}
