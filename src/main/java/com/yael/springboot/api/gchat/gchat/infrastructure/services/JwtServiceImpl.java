package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getDateExpire;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getSecretKey;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;




@Service
public class JwtServiceImpl implements IJwtService {

    @Override
    public String createToken(Collection<GrantedAuthority> roles, String username) {
        try{
            Claims claims = Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(roles))
            .build();

            String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration( getDateExpire() )
                .signWith( getSecretKey() )
                .compact();

            return token;
        }catch(Exception ex){
            throw CustomException.internalServerException("Error al generar el JWT: " + ex.getMessage());
        }
    }


    @Override
    public String createToken(List<RoleEntity> roles, String username) {
        Collection<GrantedAuthority> rolesCollection = this.rolesToCollection(roles);
        return this.createToken(rolesCollection, username);
    }


    @Override
    public String createToken(String username) {
        try {
            String token = Jwts.builder()
                .subject(username)
                .expiration( getDateExpire() )
                .signWith( getSecretKey() )
                .compact();

            return token;
        } catch (Exception e) {
            throw CustomException.internalServerException("Error al generar el token sin roles: " + e.getMessage());
        }
    }


    @Override
    public Collection<GrantedAuthority> rolesToCollection(List<RoleEntity> roles) {
        Collection<GrantedAuthority> rolesCollection = roles.stream()
            .map( r -> new SimpleGrantedAuthority(r.getRole()) )
            .collect(Collectors.toList());

        return rolesCollection;
    }
}
