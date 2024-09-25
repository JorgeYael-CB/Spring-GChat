package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.UserRepository;



//1er paso JWT
@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);
        if( !user.isPresent() ) throw new UsernameNotFoundException("User not exists");

        UserEntity userDb = user.get();
        List<GrantedAuthority> authorities = userDb.getRoles()
            .stream().map( r -> new SimpleGrantedAuthority(r.getRole()))
            .collect(Collectors.toList());

        return new User(
            username,
            userDb.getPassword(),
            userDb.getIsActive(),
            true,
            true,
            true,
            authorities
        );
    }




}
