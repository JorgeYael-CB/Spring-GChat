package com.yael.springboot.api.gchat.gchat.application.dtos.photos;




public class PhotoPreviewDto {

    private String image;
    private Long countLikes;
    private Long id;


    public PhotoPreviewDto(Long countLikes, String image, Long id) {
        this.countLikes = countLikes;
        this.image = image;
        this.id = id;
    }



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
