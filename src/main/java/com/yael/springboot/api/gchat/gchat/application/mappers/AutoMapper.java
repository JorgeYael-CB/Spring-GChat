package com.yael.springboot.api.gchat.gchat.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.activities.ActivityDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserPreviewDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.likes.LikeDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.messages.MessageWsDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.photos.PhotoPreviewDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerPreviewDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.Enums.EnumTypeMessage;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IActivityProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IMessageProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IPhotoPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerPreviewProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserPreviewProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.ActivityEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.LikeEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;



@Component
public class AutoMapper {

    public IActivityProjection activityEntityToActivity(ActivityEntity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivity(activity.getActivity());
        activityDto.setId(activity.getId());

        return activityDto;
    }

    public ILikeProjection likeEntityToLike(LikeEntity like){
        LikeDto likeDto = new LikeDto();
        likeDto.setCreateat(like.getCreateAt());
        likeDto.setId(like.getId());
        likeDto.setUpdatedat(like.getUpdatedAt());
        likeDto.setUser(userEntityToUserPreview(like.getUser()));

        return likeDto;
    }

    public IMessageProjection messageEntityToMessage( MessageEntity msg ){
        MessageDto message = new MessageDto();

        message.setContent(msg.getContent());
        message.setCreateat(msg.getCreateAt());
        message.setId(msg.getId());
        message.setServerMessage(msg.getIsServerMessage());
        message.setEdited(msg.getIsEdited());
        message.setUpdatedat(msg.getUpdatedAt());
        message.setUser(userEntityToUserPreview(msg.getUser()));

        return message;
    }

    public IPhotoPreviewProjection photoEntityToPhotoPreview( PhotoEntity image ){
        if( image != null ){
            PhotoPreviewDto photo = new PhotoPreviewDto();
            photo.setId(image.getId());
            photo.setImage(image.getImage());
            return photo;
        }

        return null;
    }

    public IServerProjection serverEntityToServer( ServerEntity server ){
        ServerDto serverDto = new ServerDto();

        serverDto.setCreateat(server.getCreateAt());
        serverDto.setDescription(server.getDescription());
        serverDto.setId(server.getId());
        serverDto.setImage(this.photoEntityToPhotoPreview(server.getImage()));
        serverDto.setOwner(this.userEntityToUserPreview(server.getOwner()));
        serverDto.setUpdatedat(server.getUpdatedAt());

        List<IUserPreviewProjection> users = server.getUsers().stream().map( u -> userEntityToUserPreview(u)).toList();
        serverDto.setUsers(users);
        serverDto.setUserslimit(server.getUsersLimit());

        return serverDto;
    }

    public IUserPreviewProjection userEntityToUserPreview(UserEntity user){
        if( user != null ){
            UserPreviewDto userPreviewDto = new UserPreviewDto();
            userPreviewDto.setId(user.getId());
            userPreviewDto.setName(user.getName());
            userPreviewDto.setProfileimage(photoEntityToPhotoPreview(user.getProfileImage()));
            userPreviewDto.setRoles(user.getRoles());
            return userPreviewDto;
        }

        return null;
    }

    public IUserAuthProjection userEntityToUserAuth(UserEntity user){
        UserDto usr = new UserDto();

        usr.setAge(user.getAge());
        usr.setCity(user.getCity());
        usr.setCountry(user.getCountry());
        usr.setCreateat(user.getCreateAt());
        usr.setDescription(user.getDescription());
        usr.setEmail(user.getEmail());
        usr.setId(user.getId());
        List<IPhotoPreviewProjection> imgs = user.getImages().stream().map( i -> photoEntityToPhotoPreview(i) ).toList();
        usr.setImages(imgs);
        usr.setName(user.getName());
        usr.setProfileimage(photoEntityToPhotoPreview(user.getProfileImage()));
        usr.setRoles(user.getRoles());
        List<IServerPreviewProjection> servers = user.getServers().stream().map( s -> serverEntityToServerPreview(s) ).toList();
        usr.setServers(servers);
        usr.setUpdatedat(user.getUpdatedAt());

        return usr;
    }

    public IServerPreviewProjection serverEntityToServerPreview(ServerEntity server){
        ServerPreviewDto sv = new ServerPreviewDto();

        sv.setCreateat(server.getCreateAt());
        sv.setId(server.getId());
        sv.setImage(photoEntityToPhotoPreview(server.getImage()));
        sv.setUpdatedat(server.getUpdatedAt());

        return sv;
    }

    public MessageWsDto messageEntityToMessageWs( MessageEntity messageEntity, EnumTypeMessage type ){
        MessageWsDto msg = new MessageWsDto();

        msg.setMessageDto(messageEntityToMessage(messageEntity));
        msg.setType(type);

        return msg;
    }

}
