package com.yael.springboot.api.gchat.gchat.application.dtos.photos;

import com.yael.springboot.api.gchat.gchat.application.dtos.BaseDto;




public class PhotoDto extends BaseDto {

    private String image;
    private Long countLikes;


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Long getCountLikes() {
        return countLikes;
    }
    public void setCountLikes(Long countLikes) {
        this.countLikes = countLikes;
    }

}
