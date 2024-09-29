package com.yael.springboot.api.gchat.gchat.application.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IMessageProjection;
import com.yael.springboot.api.gchat.gchat.domain.entities.MessageEntity;




public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {
    public Page<MessageEntity> findByServerId( Long serverId, Pageable pageable );

    @Query("SELECT m FROM MessageEntity m WHERE m.server.id = ?1")
    public Page<IMessageProjection> findByServerIdProjections( Long serverId, Pageable pageable );
}
