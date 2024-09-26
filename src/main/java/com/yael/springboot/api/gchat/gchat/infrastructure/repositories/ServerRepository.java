package com.yael.springboot.api.gchat.gchat.infrastructure.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yael.springboot.api.gchat.gchat.domain.entities.ServerEntity;




@Repository
public interface ServerRepository extends JpaRepository<ServerEntity, Long> {
    @Query("SELECT s FROM ServerEntity s WHERE s.usersLimit > SIZE(s.users) AND NOT EXISTS (SELECT u FROM s.users u WHERE u.id = ?1)")
    public List<ServerEntity> findRandomServer(Long userId, Pageable pageable);
}
