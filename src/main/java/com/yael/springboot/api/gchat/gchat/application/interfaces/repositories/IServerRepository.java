package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;


public interface IServerRepository {

    public List<ServerEntity> findRandomServer(Long userId, Pageable pageable);
    public Optional<ServerEntity> findById(Long id);
    public ServerEntity save(ServerEntity server);
    public Optional<IServerProjection> findServerById(Long serverId);

}
