package com.yael.springboot.api.gchat.gchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.yael.springboot.api.gchat.gchat.application.mappers.UserMapper;
import com.yael.springboot.api.gchat.gchat.infrastructure.repositories.UserRepository;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UserRepository userRepository; // Inyectar el repositorio aquí

    @Autowired
    private UserMapper userMapper; // Inyectar el mapper aquí


    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


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
            .addFilter( new JwtAuthToken(authenticationManager(), userRepository, userMapper) ) // primer filtro (validar datos del usuario)
            .addFilter( new JwtAuthValidationFilter(authenticationManager()) ) // segundo filtro (en caso de que expire la sesion)
            .csrf( config -> config.disable()) // solo sirve para el HTML del servidor
            .sessionManagement( managment -> managment
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).build();
    }


}
