package com.yael.springboot.api.gchat.gchat.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.CONTENT_TYPE;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.HEADER_AUTHORIZATION;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.PREFIX_TOKEN;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JwtAuthToken extends UsernamePasswordAuthenticationFilter {

    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtAuthToken(AuthenticationManager authenticationManager, IJwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserEntity user;
        String password = null;
        String email = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(),
            UserEntity.class);

            password = user.getPassword();
            email = user.getEmail();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(auth);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> res = new HashMap<>();
        res.put("err", "Password or email is not valid");
        res.put("date", new Date());
        res.put("status", 401);

        System.out.println(failed.getMessage());

        response.getWriter()
            .write( new ObjectMapper().writeValueAsString(res) );
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String email = user.getUsername();

        Collection<GrantedAuthority> roles = user.getAuthorities();
        String token = jwtService.createToken(roles, email);

        ResponseService<Object> res = new ResponseService<>();
        res.setData(email);
        res.setToken(token);
        res.setStatus(200);

        response.setStatus(200);
        response.setContentType(CONTENT_TYPE);
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        response.getWriter()
            .write( new ObjectMapper().writeValueAsString(res) );
    }


}
