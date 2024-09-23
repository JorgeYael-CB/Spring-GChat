package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;





public class RegisterUserDto extends LoginUserDto {

    @NotBlank
    @Size(min = 4, max = 70)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
