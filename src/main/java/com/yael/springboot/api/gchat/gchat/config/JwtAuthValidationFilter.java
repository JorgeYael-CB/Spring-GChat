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
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getContentType;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getHeaderAuthorization;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getPrefixToken;
import static com.yael.springboot.api.gchat.gchat.config.JwtEnvs.getSecretKey;

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
        String header = request.getHeader(getHeaderAuthorization());

        if( header == null || !header.startsWith(getPrefixToken()) ){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(getPrefixToken(), "");

        try{
            Claims claims = Jwts.parser().verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

            String username = (String)claims.getSubject();
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
                username,
                null,
                roles
            );

            SecurityContextHolder
                .getContext()
                .setAuthentication(auth);

            chain.doFilter(request, response);
        }catch(Exception ex){
            Map<String, Object> body = new HashMap<>();
            System.out.println(ex.getMessage());

            body.put("err", "Session expired");
            body.put("date", new Date());
            body.put("status", 401);

            response.getWriter().write(
                new ObjectMapper().writeValueAsString(body)
            );
            response.setStatus(401);
            response.setContentType(getContentType());
        }
    }


}
