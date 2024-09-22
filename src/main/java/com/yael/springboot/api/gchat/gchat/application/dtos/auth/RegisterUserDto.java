package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;





public class RegisterUserDto extends LoginUserDto {

    @NotBlank
    @Size(min = 4, max = 70)
    private String name;
    private MultipartFile profileImage;

    @Size(min=5, max=700)
    private String description;

    @NotNull
    @Min(value=18)
    @Max(value=80)
    private Integer age;

    private List<Long> activities; // buscar mediante el Id

    private String country;




    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Long> getActivities() {
        return activities;
    }

    public void setActivities(List<Long> activities) {
        this.activities = activities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
