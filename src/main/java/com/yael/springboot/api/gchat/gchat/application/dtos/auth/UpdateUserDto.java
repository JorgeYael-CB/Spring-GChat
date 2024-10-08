package com.yael.springboot.api.gchat.gchat.application.dtos.auth;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;




public class UpdateUserDto {

    private Long userId;

    @Size(min = 4, max = 70)
    private String name;

    private MultipartFile profileImage;
    @Size(min=8, max=250)
    private String description;

    @Min(value=18)
    @Max(value=80)
    private Integer age;

    @Size(min=2, max=4)
    private String country;

    @Size(min=2, max=50)
    private String city;

    private List<Integer> activities;




    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Integer> getActivities() {
        return activities;
    }

    public void setActivities(List<Integer> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdateUserDto{");
        sb.append("userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", profileImage=").append(profileImage);
        sb.append(", description=").append(description);
        sb.append(", age=").append(age);
        sb.append(", country=").append(country);
        sb.append(", activities=").append(activities);
        sb.append('}');
        return sb.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
