package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IServerProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;



public interface JpaServerRepository extends JpaRepository<ServerEntity, Long> {
    @Query("SELECT s FROM ServerEntity s WHERE s.usersLimit > SIZE(s.users) AND NOT EXISTS (SELECT u FROM s.users u WHERE u.id = ?1)")
    public List<ServerEntity> findRandomServer(Long userId, Pageable pageable);

    @Query("SELECT s FROM ServerEntity s WHERE s.id = ?1")
    public Optional<IServerProjection> findServerById(Long serverId);
}
