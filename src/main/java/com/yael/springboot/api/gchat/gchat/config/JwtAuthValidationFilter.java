package com.yael.springboot.api.gchat.gchat.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.CONTENT_TYPE;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.HEADER_AUTHORIZATION;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.PREFIX_TOKEN;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.SECRET_KEY;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class JwtAuthValidationFilter extends BasicAuthenticationFilter {

    public AuthenticationManager authenticationManager;


    public JwtAuthValidationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if( header == null || !header.startsWith(PREFIX_TOKEN) ){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try{
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

            String username = (String)claims.get("username");
            Object authorities = claims.get("authorities");

            Collection<? extends GrantedAuthority> roles = Arrays.asList(
                new ObjectMapper()
                .addMixIn(
                    SimpleGrantedAuthority.class,
                    SimpleGrantedAuthorityJsonCreator.class
                )
                .readValue(
                    authorities.toString().getBytes(),
                    SimpleGrantedAuthority[].class
                )
            );

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                authorities,
                null,
                roles
            );

            SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
            chain.doFilter(request, response);
        }catch(Exception ex){
            Map<String, Object> body = new HashMap<>();
            body.put("err", "Session expired");
            body.put("date", new Date());
            body.put("status", 401);

            response.getWriter().write(
                new ObjectMapper().writeValueAsString(body)
            );
            response.setStatus(401);
            response.setContentType(CONTENT_TYPE);
        }
    }


}
