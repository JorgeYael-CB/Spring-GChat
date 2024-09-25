package com.yael.springboot.api.gchat.gchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

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
                .requestMatchers(HttpMethod.GET, "/images/**").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.GET, "/api/auth/get-user/**").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // ruta permitida
                .anyRequest().authenticated() // rutas bloqueadas
            )
            .addFilter( new JwtAuthToken(authenticationManager()) ) // primer filtro (validar datos del usuario)
            .addFilter( new JwtAuthValidationFilter(authenticationManager()) ) // segundo filtro (en caso de que expire la sesion)
            .csrf( config -> config.disable()) // solo sirve para el HTML del servidor
            .sessionManagement( managment -> managment
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).build();
    }


}
