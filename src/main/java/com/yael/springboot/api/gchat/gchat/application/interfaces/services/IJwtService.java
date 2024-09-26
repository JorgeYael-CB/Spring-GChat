package com.yael.springboot.api.gchat.gchat.application.interfaces.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;





public interface IJwtService {

    public String createToken( Collection<GrantedAuthority> roles, String username );
    public String createToken( String username );
    public String createToken( List<RoleEntity> roles, String username );
    public Collection<GrantedAuthority> rolesToCollection( List<RoleEntity> roles );

}
