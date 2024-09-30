package com.yael.springboot.api.gchat.gchat.application.dtos.photos;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;




public class PhotoPreviewDto implements IPhotoPreviewProjection {


    private String image;
    private Long id;


    public PhotoPreviewDto(){}


    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public Long getId() {
        return id;
    }

}
