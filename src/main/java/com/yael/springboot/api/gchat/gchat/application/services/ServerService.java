package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.server.ServerDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.Enums.EnumTypeMessage;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IServerRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMessageWs;
import com.yael.springboot.api.gchat.gchat.application.mappers.MessageMapper;
import com.yael.springboot.api.gchat.gchat.application.mappers.ServerMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;




@Service
public class ServerService {

    @Autowired
    ServerMapper serverMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    IServerRepository serverRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    GetUserByAuth getUserByAuth;
    @Autowired
    IMessageWs messageWsService;
    @Autowired
    IJwtService jwtService;


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

        //TODO: notificar a todos los usuarios mediante websockets que alguien se conecto al servidior y emitir un mensaje
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setServer(serverDb);
        messageEntity.setIsServerMessage(true);
        messageEntity.setContent("Welcome " + userDB.getName() + " to new server!.");
        serverDb.getMessages().add(messageEntity);

        EnumTypeMessage type = EnumTypeMessage.USER_JOINED_SERVER;
        messageWsService.sendMessageToClients(messageMapper.messageEntityToMessageWs(messageEntity, type));

        userRepository.save(userDB);
        serverRepository.save(serverDb);

        String token = this.jwtService.createToken(userDB.getRoles(), userDB.getEmail());

        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(serverDb));
        response.setStatus(200);
        response.setDate(new Date());
        response.setToken(token);

        return response;
    }


    @Transactional
    public ResponseService<ServerDto> joinRandom(){
        UserEntity user = getUserByAuth.getUser();

        Pageable limitOne = PageRequest.of(0, 1);
        Optional<ServerEntity> server = serverRepository.findRandomServer(user.getId(), limitOne).stream().findFirst();
        ServerEntity serverDb;
        int status = 200;

        if( !server.isPresent() ){
            serverDb = new ServerEntity();
            serverRepository.save(serverDb);
            status = 201;
        } else {
            serverDb = server.get();
        }

        serverDb.setDescription("Este servidor fue generado de manera automatica por el servidor, disfruten del contenido!");

        serverDb.getUsers().add(user);
        user.getServers().add(serverDb);

        userRepository.save(user);
        serverRepository.save(serverDb);

        String token = this.jwtService.createToken(user.getRoles(), user.getEmail());

        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(serverDb));
        response.setToken(token);

        response.setStatus(status);
        response.setDate(new Date());

        return response;
    }


    @Transactional
    public void delete(){}


    @Transactional(readOnly=true)
    public ResponseService<ServerDto> getServerDetails( Long serverId ){
        Optional<ServerEntity> server = serverRepository.findById(serverId);
        if( !server.isPresent() ) throw CustomException.notFoundException("Server not found");

        ServerEntity serverDb = server.get();
        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(serverDb));
        response.setStatus(200);
        response.setDate(new Date());
        response.setToken("TOKEN");

        return response;
    }


    @Transactional
    public ResponseService<ServerDto> create(){
        Optional<UserEntity> user = userRepository.findByEmail(this.getUserByAuth.getUsernameLogged());
        if( !user.isPresent() ) throw CustomException.notFoundException("Oops! try again later.");

        UserEntity userDb = user.get();

        ServerEntity server = new ServerEntity();
        server.setOwner(userDb);

        userDb.getServers().add(server);
        server.getUsers().add(userDb);

        serverRepository.save(server);
        userRepository.save(userDb);

        String token = this.jwtService.createToken(userDb.getRoles(), userDb.getEmail());

        ResponseService<ServerDto> response = new ResponseService<>();
        response.setData(serverMapper.serverEntityToServerDto(server));
        response.setDate(new Date());
        response.setStatus(201);
        response.setToken(token);

        return response;
    }

}
