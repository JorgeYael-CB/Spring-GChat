package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;



public class PhotoEntity extends BaseEntity {

    private String image;
    private List<LikeEntity> likes = new ArrayList<>();;


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<LikeEntity> getLikes() {
        return likes;
    }
    public void setLikes(List<LikeEntity> likes) {
        this.likes = likes;
    }

}

