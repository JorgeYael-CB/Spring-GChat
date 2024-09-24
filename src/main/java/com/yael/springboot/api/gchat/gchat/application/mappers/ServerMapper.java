package com.yael.springboot.api.gchat.gchat.application.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;




@Component
public class ServerMapper {

    @Autowired
    UserMapper userMapper;


    public ServerDto serverEntityToServerDto( ServerEntity serverEntity ){
        ServerDto server = new ServerDto();
        List<UserDto> users = new ArrayList<>();

        server.setId(serverEntity.getId());
        server.setMessages(serverEntity.getMessages());

        server.setOwner( userMapper.userEntityToUserDto(serverEntity.getOwner()) );

        serverEntity.getUsers()
            .stream()
            .forEach( u -> {
                UserDto user = userMapper.userEntityToUserDto(u);
                user.setServers(new ArrayList<>());
                users.add(user);
            });

        server.setUsers( users );

        return server;
    }
}
