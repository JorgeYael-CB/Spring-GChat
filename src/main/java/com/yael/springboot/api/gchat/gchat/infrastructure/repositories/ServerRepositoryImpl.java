package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IServerRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.JpaServerRepository;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;




@Repository
public class ServerRepositoryImpl implements IServerRepository {

    @Autowired
    JpaServerRepository jpaServerRepository;


    @Override
    public List<ServerEntity> findRandomServer(Long userId, Pageable pageable) {
        return jpaServerRepository.findRandomServer(userId, pageable);
    }

    @Override
    public Optional<ServerEntity> findById(Long id) {
        return jpaServerRepository.findById(id);
    }

    @Override
    public ServerEntity save(ServerEntity server) {
        return jpaServerRepository.save(server);
    }

    @Override
    public Optional<IServerProjection> findServerById(Long serverId) {
        return jpaServerRepository.findServerById(serverId);
    }
}
