package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;



public class UpdatePasswordOrEmailDto {

    @Email
    private String email;

    @Length(min=4, max=60)
    private String password;

    @NotEmpty
    private String code;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
