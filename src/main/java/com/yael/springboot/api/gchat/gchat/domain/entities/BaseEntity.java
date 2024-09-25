package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createAt;
    @UpdateTimestamp
    private Date updatedAt;
    private Boolean isActive = true;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BaseEntity{");
        sb.append("id=").append(id);
        sb.append(", createAt=").append(createAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }

}
