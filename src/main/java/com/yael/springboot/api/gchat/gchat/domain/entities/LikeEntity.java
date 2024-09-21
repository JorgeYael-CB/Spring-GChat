package com.yael.springboot.api.gchat.gchat.domain.entities;

import jakarta.persistence.ManyToOne;




public class LikeEntity extends BaseEntity {

    private int likes;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private PhotoEntity photoEntity;



    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PhotoEntity getPhotoEntity() {
        return photoEntity;
    }

    public void setPhotoEntity(PhotoEntity photoEntity) {
        this.photoEntity = photoEntity;
    }

}
