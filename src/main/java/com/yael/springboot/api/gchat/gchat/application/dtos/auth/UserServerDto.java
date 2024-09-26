package com.yael.springboot.api.gchat.gchat.application.dtos.auth;




public class UserServerDto {

    private String image;
    private Long id;



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
