package com.yael.springboot.api.gchat.gchat.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserServerDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.photos.PhotoPreviewDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;

@Component
public class UserMapper {
    public UserDto userEntityToUserDto(UserEntity user) {
        UserDto userDto = new UserDto();

        // Mapear imágenes
        List<PhotoPreviewDto> photoPreviewDtos = mapImages(user.getImages());

        // Mapear imagen de perfil
        PhotoPreviewDto profileImage = mapProfileImage(user.getProfileImage());

        // Setear propiedades básicas
        setBasicProperties(userDto, user);

        // Mapear imágenes y servidores
        userDto.setImages(photoPreviewDtos);
        userDto.setProfileImage(profileImage);
        userDto.setServers(mapServers(user.getServers()));

        // Mapear roles
        userDto.setRoles(user.getRoles().stream()
            .map(r -> r.getRole())
            .collect(Collectors.toList()));

        // Setear email si existe
        if (user.getEmail() != null) {
            userDto.setEmail(user.getEmail());
        }

        return userDto;
    }

    private List<PhotoPreviewDto> mapImages(List<PhotoEntity> images) {
        return images.stream()
            .map(i -> new PhotoPreviewDto(Long.valueOf(i.getLikes().size()), i.getImage(), i.getId()))
            .collect(Collectors.toList());
    }

    private PhotoPreviewDto mapProfileImage(PhotoEntity profileImage) {
        if (profileImage != null) {
            return new PhotoPreviewDto(Long.valueOf(profileImage.getLikes().size()),
                profileImage.getImage(),
                profileImage.getId());
        }
        return null;
    }

    private void setBasicProperties(UserDto userDto, UserEntity user) {
        userDto.setActivities(user.getActivities());
        userDto.setAge(user.getAge());
        userDto.setCountry(user.getCountry());
        userDto.setDescription(user.getDescription());
        userDto.setName(user.getName());
        userDto.setIsActive(user.getIsActive());
        userDto.setCreateAt(user.getCreateAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setId(user.getId());
    }

    private List<UserServerDto> mapServers(List<ServerEntity> servers) {
        return servers.stream()
            .map(u -> {
                UserServerDto server = new UserServerDto();
                server.setId(u.getId());
                server.setImage(u.getImage() != null ? u.getImage().getImage() : null);
                return server;
            })
            .collect(Collectors.toList());
    }
}