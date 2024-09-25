package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.application.mappers.ServerMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.ServerRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.UserRepository;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;




@Service
public class ServerService {

    @Autowired
    ServerMapper serverMapper;
    @Autowired
    ServerRepository serverRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetUserByAuth getUserByAuth;


    public ResponseService<ServerDto> joinById( Long serverId ){
        Optional<ServerEntity> server = serverRepository.findById(serverId);
        if( !server.isPresent() ) throw CustomException.notFoundException("Server not found.");
        Optional<UserEntity> user = userRepository.findByEmail(this.getUserByAuth.getUsernameLogged());
        if( !user.isPresent() ) throw CustomException.notFoundException("Oops! try again later.");

        ServerEntity serverDb = server.get();
        UserEntity userDB = user.get();

        Boolean exists = serverDb.getUsers().stream().anyMatch( u -> u.getId().equals(userDB.getId()));
        if( exists ) throw CustomException.badRequestException("the user has already joined this server.");

        serverDb.getUsers().add(userDB);
        userDB.getServers().add(serverDb);

        userRepository.save(userDB);
        serverRepository.save(serverDb);

        //TODO: notificar a todos los usuarios mediante websockets que alguien se conecto al servidior y emitir un mensaje

        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(serverDb));
        response.setStatus(200);
        response.setDate(new Date());

        return response;
    }


    public void joinRandom(){}


    public void delete(){}


    @Transactional
    public ResponseService<ServerDto> create(){
        //TODO: solo podran crear los admins o los que tienen el role de pago.
        Optional<UserEntity> user = userRepository.findByEmail(this.getUserByAuth.getUsernameLogged());
        if( !user.isPresent() ) throw CustomException.notFoundException("Oops! try again later.");

        UserEntity userDb = user.get();

        ServerEntity server = new ServerEntity();
        server.setOwner(userDb);

        userDb.getServers().add(server);
        server.getUsers().add(userDb);

        serverRepository.save(server);
        userRepository.save(userDb);

        String token = "JWT";

        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(server));
        response.setDate(new Date());
        response.setStatus(201);
        response.setToken(token);

        return response;
    }

}
