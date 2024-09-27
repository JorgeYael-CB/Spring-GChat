package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "servers")
public class ServerEntity extends BaseEntity {

    private int usersLimit = 20;
    private String description;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();

    @ManyToOne
    private UserEntity owner;

    @OneToMany(cascade=CascadeType.ALL)
    private List<MessageEntity> messages = new ArrayList<>();

    @ManyToOne
    private PhotoEntity image;



    public int getUsersLimit() {
        return usersLimit;
    }

    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public PhotoEntity getImage() {
        return image;
    }

    public void setImage(PhotoEntity image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
