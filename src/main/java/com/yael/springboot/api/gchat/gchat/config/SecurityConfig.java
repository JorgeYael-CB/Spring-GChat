package com.yael.springboot.api.gchat.gchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests( authz -> authz
                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // ruta permitida
                .anyRequest().authenticated() // rutas bloqueadas
            )
            .csrf( config -> config.disable()) // solo sirve para el HTML del servidor
            .sessionManagement( managment -> managment
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).build();
    }


}
