package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "images")
public class PhotoEntity extends BaseEntity {

    private String image;

    @OneToMany(cascade=CascadeType.ALL) // que sean borrados los likes de la foto
    private List<LikeEntity> likes = new ArrayList<>();


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

