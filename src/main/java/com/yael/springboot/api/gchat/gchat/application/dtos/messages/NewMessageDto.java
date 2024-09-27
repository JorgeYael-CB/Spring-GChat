package com.yael.springboot.api.gchat.gchat.application.dtos.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class NewMessageDto {


    @Size(min=1)
    @NotBlank
    private String content;


    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
